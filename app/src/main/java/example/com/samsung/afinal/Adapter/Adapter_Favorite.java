package example.com.samsung.afinal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.samsung.afinal.Classes.data_Favorite;
import example.com.samsung.afinal.R;

public class Adapter_Favorite extends RecyclerView.Adapter<ViewHolder_Favorite>{


    private ArrayList<data_Favorite> itemList;

    public void setData(ArrayList<data_Favorite> list){
        this.itemList = list;
    }

    @Override
    public ViewHolder_Favorite onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent,false);

        ViewHolder_Favorite holder = new ViewHolder_Favorite(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder_Favorite holder, int position) {

        data_Favorite data = this.itemList.get(position);

        holder.despcrition.setText(data.getText());
        holder.icon.setImageResource(data.getImg());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


}
