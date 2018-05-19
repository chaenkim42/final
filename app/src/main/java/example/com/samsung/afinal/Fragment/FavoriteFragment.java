package example.com.samsung.afinal.Fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

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
        //<핵심 원리>
        //어뎁터 안에다가 이전에 만들어둔 데이터틀 을 담고있는 배열을 넣고
        //그 어뎁터를 recyclerview에 넣습니다. 이때, recyclerview는 어뎁터를 통해서 가져온
        //list array를 어떠한 layout형태로 배열할지 layout설정을 해줘야 합니다.

        //즉, 특정 layout을 recyclerview에 넣어준 뒤에 event(버튼 같은)가 발생할 때마다 갱신 시킨 list를
        //어뎁터에 넣어주고 그 어뎁터를 recyclerview에 넣어줍니다.

        //Step1 : recyclerview의 id를 가져오기
        //이전에 프레그먼트생성에서는 return inflater.inflate.....이렇게 진행했지만
        //여기에서는 하나의 view로써 일단 저장해 둡니다.(recyclerview의 id를 가져오기 위해서)
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        button = (ImageButton) view.findViewById(R.id.plusButton);

        //Step2 : recyclerview에 layout정하기
        //이제 recyclerview에서 데이터들을 어떻게 배치할지 layout을 정하는 부분으로
        //LinearLayout을 동적으로 코드상으로 불러온뒤에
        recyclerView = view.findViewById(R.id.favoriteList);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recylerview에 만든 LinearLayout을 넣습니다.
        recyclerView.setLayoutManager(layoutManager);
        adpater = new Adapter_Favorite();

        try {
            JSONArray objects = new JSONArray("[{\"name\":\"폴더1\",\"index\":\"0\"},{\"name\":\"좋아하는 메뉴\",\"index\":\"1\"}]");
            for(int i=0; i<objects.length(); i++){
                JSONObject o = objects.getJSONObject(i);
                String name = o.getString("name");

                //Step3
                //list array를 담은 adapter를 recyclerview에 넣기
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


