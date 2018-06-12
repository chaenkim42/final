package example.com.samsung.afinal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import example.com.samsung.afinal.Classes.data_Upload;
import example.com.samsung.afinal.Interface.OnItemClickListener;
import example.com.samsung.afinal.R;

public class Adapter_Upload extends RecyclerView.Adapter<ViewHolder_Upload>{

//    private ArrayList<String> recipeContens_string = new ArrayList();
    public ArrayList<data_Upload> recipeContens = new ArrayList<>();

    OnItemClickListener onItemClickListener;

    public void setListener(OnItemClickListener onItemClickListener){this.onItemClickListener = onItemClickListener;}

    public void setData(ArrayList<data_Upload> list){this.recipeContens = list;}
    public ArrayList<data_Upload> getRecipeContens() {
        return recipeContens;
    }
    //    public void addItem(String s) {
//        recipeContens_string.add(s);
//    }
//
//    public String getItem(int position) {
//        return recipeContens_string.get(position);
//    }
//
//    public String getAllText() {
//        StringBuilder sb = new StringBuilder();
//        for (String s : recipeContens_string)
//            sb.append(s);
//
//        return sb.toString();
//    }

    @Override
    public ViewHolder_Upload onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload, parent, false);

        ViewHolder_Upload vh = new ViewHolder_Upload(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder_Upload holder, final int position) {
        holder.bindData(recipeContens.get(position));

        holder.getEditRecipe().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                onItemClickListener.onItemClick(position);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                recipeContens.get(position).setRecipeContents(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        holder.editRecipe.setText(recipeContens.get(position).getRecipeContents());

    }

    @Override
    public int getItemCount() {

        return recipeContens.size();
    }



    //ViewHolder에는 EditText를 포함하도록 합니다.
    //ViewHolder  구현은 직접.
}




