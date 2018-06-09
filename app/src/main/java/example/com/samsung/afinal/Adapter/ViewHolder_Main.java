package example.com.samsung.afinal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.samsung.afinal.Classes.data_Main;
import example.com.samsung.afinal.R;



/*
* data_Main에서 틀 하나에 들어가는 요소를
* 코드로 찢고 볶고 할 수 있도록, 작업할 수 있도록
* 아이디와 값을 연결해주는 작업입니다.
* */
public class ViewHolder_Main extends RecyclerView.ViewHolder{
    private TextView title;
    private ImageView imageURL;
    private TextView context;
    private ImageView star;

    public ViewHolder_Main(View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.foodTitle);
        this.imageURL = itemView.findViewById(R.id.foodImage);
        this.context = itemView.findViewById(R.id.foodContext);
        this.star = itemView.findViewById(R.id.star);
    }

    public void bindData(data_Main data_main){
        this.title.setText(data_main.getTitle());
        this.imageURL.setImageResource(data_main.getImageURL());
        this.context.setText(data_main.getContext());
        this.star.setImageResource(data_main.getIsClicked());
    }
}
