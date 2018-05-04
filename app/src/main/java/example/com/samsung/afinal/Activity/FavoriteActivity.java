package example.com.samsung.afinal.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import example.com.samsung.afinal.Adapter.Adapter_Favorite;
import example.com.samsung.afinal.Classes.data_Favorite;
import example.com.samsung.afinal.R;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView mVerticalView;
    private Adapter_Favorite mAdapter;
    private LinearLayoutManager mLayoutManager;

    private ImageButton plusButton;

    ArrayList<data_Favorite> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        plusButton = findViewById(R.id.plusButton);

        //RecyclerView Bindling
        mVerticalView = (RecyclerView)findViewById(R.id.favoriteList);
        //init Data

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mVerticalView.setLayoutManager(mLayoutManager);

        mAdapter = new Adapter_Favorite();



        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.add(new data_Favorite(R.mipmap.ic_launcher, "~번째 데이터"));
                mAdapter.setData(data);

                mVerticalView.setAdapter(mAdapter);

            }
        });


    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case 1:
                Toast.makeText(this,"test1", Toast.LENGTH_LONG).show();

                return true;
            case 2:
                Toast.makeText(this, "test2", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}
