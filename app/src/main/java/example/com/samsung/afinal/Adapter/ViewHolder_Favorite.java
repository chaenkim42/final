package example.com.samsung.afinal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.samsung.afinal.R;

public class ViewHolder_Favorite extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

    public ImageView icon;
    public TextView despcrition;

    public ViewHolder_Favorite(View itemView){
        super(itemView);

        icon = itemView.findViewById(R.id.imgIcon);
        despcrition = (TextView)itemView.findViewById(R.id.description);
        itemView.setOnCreateContextMenuListener(this);
    }


    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("폴더작업");
        contextMenu.add(0,1,0,"rename");
        contextMenu.add(0,2,0,"delete");
    }



}
