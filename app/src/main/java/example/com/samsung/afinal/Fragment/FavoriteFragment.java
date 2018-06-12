package example.com.samsung.afinal.Fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import example.com.samsung.afinal.Adapter.Adapter_Favorite;
import example.com.samsung.afinal.Classes.MongoLabClient;
import example.com.samsung.afinal.Classes.data_Favorite;
import example.com.samsung.afinal.Interface.OnItemClickListener;
import example.com.samsung.afinal.R;

import static example.com.samsung.afinal.Activity.MainActivity.mongoLabClient;
import static example.com.samsung.afinal.Activity.MainActivity.API_KEY;
import static example.com.samsung.afinal.Activity.MainActivity.COLLECTION_FOLDER;
import static example.com.samsung.afinal.Activity.MainActivity.DATABASE;
import static example.com.samsung.afinal.Activity.MainActivity.USER_SESSION;
import static example.com.samsung.afinal.Activity.MainActivity.fragmentContainer;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private Adapter_Favorite adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<data_Favorite> list = new ArrayList<>();
    private ImageButton imageButton;

    private ArrayList<String> allFolders = new ArrayList<String>();
    private JSONArray allFolderJSON = new JSONArray();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // 폴더명 목록 받기
        mongoLabClient = new MongoLabClient(API_KEY, DATABASE, COLLECTION_FOLDER);
        String[] split = mongoLabClient.find().split("\"name\"");
        for(int i=0; i<split.length-1; i++){
            String string = split[i+1].split("\"")[1];
            allFolders.add(string);
        }

        // 폴더 json 받기
        try {
            for (int i = 0; i < allFolders.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", allFolders.get(i));
                mongoLabClient = new MongoLabClient(API_KEY, DATABASE, COLLECTION_FOLDER);
                String mongoResultString = mongoLabClient.findOne(jsonObject.toString());
                JSONObject folderObject = new JSONObject(mongoResultString);
                allFolderJSON.put(folderObject);
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        imageButton = view.findViewById(R.id.plusButton);
        fragmentContainer.setVisibility(View.VISIBLE);

        recyclerView = view.findViewById(R.id.favoriteList);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter_Favorite();

        // recyclerView 에 붙일 list item 받기 (현재 사용자에게 속한 폴더만 받음)
        try {
            String userOid = USER_SESSION.getString("_id");
            JSONArray listObjects = new JSONArray();
            for(int i=0; i<allFolderJSON.length(); i++){
                if(allFolderJSON.get(i).toString().contains(userOid)){
                    listObjects.put(allFolderJSON.get(i));
                }
            }
            for (int j = 0; j < listObjects.length(); j++) {
                JSONObject o = listObjects.getJSONObject(j);
                String name = o.getString("name");
                data_Favorite newData = new data_Favorite(R.mipmap.folder, name);
                list.add(newData);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter.setData(list);
        recyclerView.setAdapter(adapter);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("폴더 추가");
                alertDialog.setMessage("새로운 폴더명을 입력하세요");
                // TODO : set default folder name

                // EditText 삽입하기
                final EditText et = new EditText(getActivity());
                et.setPrivateImeOptions("defaultInputmode=korean;");
                alertDialog.setView(et);

                // 확인 버튼 설정
                alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Text 값 받아서 로그 남기기
                        String inputValue = et.getText().toString();
                        if(!inputValue.isEmpty()) {
                            JSONObject newFolderObject = new JSONObject();
                            try {
//                                Log.e("folder - check", USER_SESSION.getString("_id").substring(9,33));
                                newFolderObject.put("name", inputValue);
                                newFolderObject.put("userBelong", USER_SESSION);
                                newFolderObject.put("recipes","");
                                allFolders.add(inputValue);
                                allFolderJSON.put(newFolderObject);

                                mongoLabClient = new MongoLabClient(API_KEY, DATABASE, COLLECTION_FOLDER);
                                mongoLabClient.insert(newFolderObject.toString());
                                data_Favorite newData = new data_Favorite(R.mipmap.folder, inputValue);
                                list.add(newData);
                                adapter.setData(list);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getContext(), "폴더 생성 실패", Toast.LENGTH_SHORT).show();
                            }
                            // Dialog 닫기
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "폴더명을 입력하세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // 취소 버튼 설정
                alertDialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });

                // 창 띄우기
                alertDialog.show();


            }
        });
        return view;
    }
}


