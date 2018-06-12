package example.com.samsung.afinal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import example.com.samsung.afinal.Classes.data_Upload;
import example.com.samsung.afinal.R;

public class ViewHolder_Upload extends RecyclerView.ViewHolder{

    public EditText editRecipe;

    public ViewHolder_Upload(View itemView) {
        super(itemView);
        this.editRecipe = itemView.findViewById(R.id.edit_recipe);
    }
    public void bindData(data_Upload data_upload){
        this.editRecipe.setText(data_upload.getRecipeContents());
    }

    public EditText getEditRecipe(){return editRecipe;}
}
