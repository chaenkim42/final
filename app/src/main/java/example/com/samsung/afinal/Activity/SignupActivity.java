package example.com.samsung.afinal.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import example.com.samsung.afinal.Handler.BackPressCloseHandler;
import example.com.samsung.afinal.R;

public class SignupActivity extends AppCompatActivity {

    BackPressCloseHandler backPress;
    EditText inputId, inputPw, confirmPw, inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputId = findViewById(R.id.signup_Id);
        inputPw = findViewById(R.id.signup_pw);
        confirmPw = findViewById(R.id.signup_pwRetype);
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
