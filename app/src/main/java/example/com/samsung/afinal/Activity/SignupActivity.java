package example.com.samsung.afinal.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import example.com.samsung.afinal.Handler.BackPressCloseHandler;
import example.com.samsung.afinal.R;

public class SignupActivity extends AppCompatActivity {

    BackPressCloseHandler backPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed() {
        this.finish();
    }

    public void cancelBtnOnClick(View view){
        this.finish();
    }
}
