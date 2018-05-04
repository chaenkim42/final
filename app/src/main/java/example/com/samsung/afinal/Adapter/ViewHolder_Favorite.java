package example.com.samsung.afinal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.samsung.afinal.R;

public class ViewHolder_Favorite extends RecyclerView.ViewHolder{

    public ImageView icon;
    public TextView despcrition;

    public ViewHolder_Favorite(View itemView){
        super(itemView);

        icon = itemView.findViewById(R.id.imgIcon);
        despcrition = (TextView)itemView.findViewById(R.id.description);
    }


}
