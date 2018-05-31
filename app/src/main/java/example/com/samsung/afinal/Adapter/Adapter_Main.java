package example.com.samsung.afinal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import example.com.samsung.afinal.Classes.data_Main;
import example.com.samsung.afinal.R;

public class Adapter_Main extends RecyclerView.Adapter<ViewHolder_Main>{

    ArrayList<data_Main> items;

    public void setData(ArrayList<data_Main> items){this.items = items;}

    @Override
    public ViewHolder_Main onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);

        ViewHolder_Main holder = new ViewHolder_Main(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder_Main holder, int position) {

        holder.bindData(items.get(position));
    }

    @Override
    public int getItemCount() {
        //여기서 return 하는 값만큼 recyclerView항목이 늘어납니다.
        // 즉, 멤버 변수에 ArrayList<data_Main>해줄것인데 이것의 length를 리턴해주면 그만큼
        // recyclerview의 개수가 늘어납니다.
        return items.size();
    }
}
