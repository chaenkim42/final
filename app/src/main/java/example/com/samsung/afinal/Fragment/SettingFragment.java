package example.com.samsung.afinal.Fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import example.com.samsung.afinal.R;

public class SettingFragment extends PreferenceFragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference_setting);
    }
}
