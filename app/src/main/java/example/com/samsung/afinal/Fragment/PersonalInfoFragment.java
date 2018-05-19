package example.com.samsung.afinal.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import example.com.samsung.afinal.R;

import static example.com.samsung.afinal.Activity.MainActivity.fragmentContainer;

public class PersonalInfoFragment extends Fragment{
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personalinfo, container, false);
//        RelativeLayout fragmentContainer;
//        fragmentContainer = getView().findViewById(R.id.fragment_container);
        fragmentContainer.setVisibility(View.VISIBLE);
        return view;
    }
}
