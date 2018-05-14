package example.com.samsung.afinal.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(new data_Favorite(R.mipmap.ic_launcher, "~번째 데이터"));
                adpater.setData(list);

                recyclerView.setAdapter(adpater);

            }
        });
        return view;

    }
}


