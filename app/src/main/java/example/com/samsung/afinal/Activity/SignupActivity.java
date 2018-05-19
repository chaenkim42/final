package example.com.samsung.afinal.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import example.com.samsung.afinal.Classes.MongoLabClient;
import example.com.samsung.afinal.Handler.BackPressCloseHandler;
import example.com.samsung.afinal.R;

public class SignupActivity extends AppCompatActivity {

    private BackPressCloseHandler backPress;
    private String id, pw, pwRetype, email;
    private EditText inputId, inputPw, inputPwRetype, inputEmail;
    private TextView pwConfirmLabel, pwSameCheckLabel;
    private CheckBox checkBox;
    private Button idDupCheckBtn, cancelBtn, signupBtn;

    // 아이디가 중복체크완료 되었는지 여부
    private boolean isIdConfirmed = false;
    // 아이디가 중복인지 여부
    private boolean isIdDuplicated = false;

    // Information to access to mLab
    private String API_KEY = "XhgaoR68m-lW9uUX1WGMO9tOmd0TPvlQ";
    private String DATABASE = "dbchtest";
    private String COLLECTION = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputId = findViewById(R.id.signup_id);
        inputPw = findViewById(R.id.signup_pw);
        inputPwRetype = findViewById(R.id.signup_pwRetype);
        inputEmail = findViewById(R.id.signup_email);
        pwConfirmLabel = findViewById(R.id.signup_pwConfirmLabel);
        pwSameCheckLabel = findViewById(R.id.signup_pwSameCheckLabel);
        checkBox = findViewById(R.id.signup_checkBox);
        idDupCheckBtn = findViewById(R.id.signup_idDupCheckBtn);
        cancelBtn = findViewById(R.id.signup_cancelBtn);
        signupBtn = findViewById(R.id.signup_confirmBtn);

        inputId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 아이디 중복체크를 다시 해야 하도록 flag 변경.
                isIdConfirmed = false;
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        idDupCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = inputId.getText().toString();
                try{
                    MongoLabClient mlClient = new MongoLabClient(API_KEY);
                    String response = mlClient.find();
                    String userId;
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject o = jsonArray.getJSONObject(i);
                        userId = o.getString("id");
                        if(userId.equals(id)){
                            isIdDuplicated = true;
                            break;
                        }
                    }
                    if( ! isIdDuplicated){
                        isIdConfirmed = true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(isIdDuplicated){
                    inputId.setText("");
                    Toast.makeText(SignupActivity.this, "중복된 ID 입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();
                }

            }
        });

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
