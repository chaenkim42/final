package example.com.samsung.afinal.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import example.com.samsung.afinal.Adapter.Adapter_Favorite;
import example.com.samsung.afinal.Classes.data_Favorite;
import example.com.samsung.afinal.R;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private Adapter_Favorite adpater;
    private LinearLayoutManager layoutManager;
    private ArrayList<data_Favorite> list = new ArrayList<>();
    private ImageButton button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_favorite, container, false);
        button = (ImageButton) view.findViewById(R.id.plusButton);
        recyclerView = view.findViewById(R.id.favoriteList);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        adpater = new Adapter_Favorite();

        try {
            JSONArray objects = new JSONArray("[{\"name\":\"폴더1\",\"index\":\"0\"},{\"name\":\"좋아하는 메뉴\",\"index\":\"1\"}]");
            for(int i=0; i<objects.length(); i++){
                JSONObject o = objects.getJSONObject(i);
                String name = o.getString("name");

                list.add(new data_Favorite(R.mipmap.ic_launcher, name));
                adpater.setData(list);
                recyclerView.setAdapter(adpater);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        button.setOnClickListener(new View.OnClickListener() {
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
                        data_Favorite newData = new data_Favorite(R.mipmap.ic_launcher, inputValue);
                        list.add(newData);
                        adpater.setData(list);
                        recyclerView.setAdapter(adpater);

                        // Dialog 닫기
                        dialog.dismiss();
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


