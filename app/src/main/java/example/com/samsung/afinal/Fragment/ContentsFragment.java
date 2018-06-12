package example.com.samsung.afinal.Fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import example.com.samsung.afinal.R;

import static example.com.samsung.afinal.Activity.MainActivity.fragmentContainer;

public class ContentsFragment  extends Fragment{

    public static ContentsFragment newInstance(int imageURL, String txt) {
        
        Bundle args = new Bundle();
        args.putInt("imageURL", imageURL);
        args.putString("text",txt);
        
        ContentsFragment fragment = new ContentsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contents, container, false);

        ImageView imageView = view.findViewById(R.id.contentsImage);
        TextView textView = view.findViewById(R.id.contentsText);
        int tmpImg = getArguments().getInt("imageURL");
        String tmpTxt = getArguments().getString("text");

        Log.d("ViewPager","ddd");
        imageView.setImageResource(tmpImg);
        textView.setText(tmpTxt);
        textView.setTextColor(Color.WHITE);

        fragmentContainer.setVisibility(View.VISIBLE);
        return view;
    }
}
