package example.com.samsung.afinal.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.samsung.afinal.Adapter.Adapter_Upload;
import example.com.samsung.afinal.Classes.data_Upload;
import example.com.samsung.afinal.Interface.OnItemClickListener;
import example.com.samsung.afinal.R;

public class UploadFragment extends Fragment{

    static final String CHECK = "BACKUP";


    String temp = null;
    ImageButton explanationPlus;
    Button editIngredientOKButton;
    Button editIngredientCancelButton;
    Button editSave;
    EditText editTitle;
    EditText editTime;
    EditText editIngredient;
    LinearLayout linearLayout;
    TextView ingredientView;


    LinearLayoutManager mLayoutManager;
    Adapter_Upload adapter_upload;
    RecyclerView recyclerView_Editrecipe;


    String backupTitle;
    String backupTime;
    String backupIngredients;
    ArrayList<data_Upload> backupRecyclerItems = new ArrayList<>();
    ArrayList<String> backupIngredients_ver_2 = new ArrayList<>();





    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, container, false);


        //////////////////////////////////////////////////////////

        editSave = view.findViewById(R.id.saveButton);
        editTitle = view.findViewById(R.id.edit_foodTitle);
        editTime = view.findViewById(R.id.edit_cookTime);

        //////////////////////////////////////////////////////////

        linearLayout = view.findViewById(R.id.view_ingredient2);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        ingredientView = new TextView(getContext());
        linearLayout.addView(ingredientView);



        //////////////////////////////////////////////////////////

        recyclerView_Editrecipe = view.findViewById(R.id.edit_list);

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_Editrecipe.setLayoutManager(mLayoutManager);

        adapter_upload = new Adapter_Upload();
        backupRecyclerItems.add(new data_Upload(temp));
        adapter_upload.setData(backupRecyclerItems);
        adapter_upload.setListener(onItemClickListener);
        recyclerView_Editrecipe.setAdapter(adapter_upload);


        explanationPlus = view.findViewById(R.id.recipe_contents_plus);
        explanationPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                backupRecyclerItems.add(new data_Upload(temp));
                adapter_upload.notifyDataSetChanged();
            }
        });

        ///////////////////////////////////////////////////////////////////


        editIngredientOKButton = view.findViewById(R.id.edit_ingredient_ok);
        editIngredient = view.findViewById(R.id.edit_ingredient);

        editIngredientOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView tempText = new TextView(getContext());
                tempText.setBackgroundResource(R.drawable.rounded_yellow);
                backupIngredients = "";
                backupIngredients += "   "+editIngredient.getText().toString() + "   ";
                tempText.setText(backupIngredients);
                linearLayout.addView(tempText);

                backupIngredients_ver_2.add(backupIngredients);

            }
        });

        editIngredientCancelButton = view.findViewById(R.id.edit_ingredient_cancel);

        editIngredientCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayout.removeAllViews();
                TextView tempText = new TextView(getContext());
                tempText.setBackgroundResource(R.drawable.rounded_yellow);
                backupIngredients = "";
                tempText.setText(backupIngredients);
                linearLayout.addView(tempText);

                for(int i=0; i<backupIngredients_ver_2.size(); i++)
                    backupIngredients_ver_2.remove(i);
            }
        });

        editSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backupTitle = editTitle.getText().toString();
                backupTime = editTime.getText().toString();
                Log.d(CHECK, "backupTitle변수 : " + backupTitle);
                Log.d(CHECK, "backupTime변수 : " + backupTime);
                Log.d(CHECK, "backupIngredients변수 : " + backupIngredients);
                for(int i=0; i<backupIngredients_ver_2.size(); i++)
                    Log.d(CHECK, "backupIngredients 변수 : " + backupIngredients_ver_2.get(i));
                for(int i=0; i<backupRecyclerItems.size(); i++)
                    Log.d(CHECK, "backupRecyclerItems 변수 : " + backupRecyclerItems.get(i).getRecipeContents());

            }
        });



        return view;
    }
}
