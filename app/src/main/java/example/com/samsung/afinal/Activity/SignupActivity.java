package example.com.samsung.afinal.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import org.json.JSONArray;
import org.json.JSONException;
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
    private String COLLECTION_USER = "users";

    Handler handler = new Handler();
    MongoLabClient mongoLabClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputId = findViewById(R.id.signup_id);
        inputPw = findViewById(R.id.signup_pw);
        inputPwRetype = findViewById(R.id.signup_pwRetype);
        pwConfirmLabel = findViewById(R.id.signup_pwConfirmLabel);
        pwSameCheckLabel = findViewById(R.id.signup_pwSameCheckLabel);
        checkBox = findViewById(R.id.signup_checkBox);
        idDupCheckBtn = findViewById(R.id.signup_idDupCheckBtn);
        cancelBtn = findViewById(R.id.signup_cancelBtn);
        signupBtn = findViewById(R.id.signup_confirmBtn);



        idDupCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!inputId.getText().toString().contains("@")){
                    setIdConfirmed(false);
                    Toast.makeText(getApplicationContext(), "올바른 이메일이 아닙니다.", Toast.LENGTH_SHORT).show();
                }else if(inputId.getText().toString().length() <=0){
                    setIdConfirmed(false);
                    Toast.makeText(getApplicationContext(), "이메일 입력 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                } else{
                    String instantId = inputId.getText().toString();
                    JSONObject dupCheck = new JSONObject();
                    try {
                        dupCheck.put("email", instantId);
                        mongoLabClient = new MongoLabClient(API_KEY, DATABASE, COLLECTION_USER);
                        String mongoResultString = mongoLabClient.findOne(dupCheck.toString());
                        BasicDBObject userObject = new BasicDBObject();
                        userObject = (BasicDBObject) JSON.parse(mongoResultString);
                        if (mongoResultString.contains("$oid")) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    inputId.setText("");
                                    Log.e("signup","isok");
                                }
                            },300);
                            setIdConfirmed(false);
                            Toast.makeText(getApplicationContext(), "중복된 이메일입니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            setIdConfirmed(true);
                            Toast.makeText(getApplicationContext(), "사용 가능한 이메일입니다.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        inputPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String instantPw = s.toString();
                if(instantPw.length()<1){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pwConfirmLabel.setText("");
                        }
                    },300);
                }else if (instantPw.length() > 3 ) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pwConfirmLabel.setTextColor(Color.GREEN);
                            pwConfirmLabel.setText("사용 가능");
                        }
                    },300);
                } else{
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pwConfirmLabel.setTextColor(Color.RED);
                            pwConfirmLabel.setText("4자이상 입력하세요");
                        }
                    },300);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}

        });

        inputPwRetype.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String instantPw = s.toString();
                if(instantPw.length()<1){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pwSameCheckLabel.setText("");
                        }
                    },300);
                }else if (instantPw.equals(inputPw.getText().toString()) ) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pwSameCheckLabel.setTextColor(Color.GREEN);
                            pwSameCheckLabel.setText("일치");
                        }
                    },300);
                } else{
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pwSameCheckLabel.setTextColor(Color.RED);
                            pwSameCheckLabel.setText("불일치");
                        }
                    },300);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}

        });


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isIdConfirmed){
                    Toast.makeText(getApplicationContext(), "이메일 중복확인 버튼을 클릭해주세요.", Toast.LENGTH_SHORT).show();
                }else if(!(pwConfirmLabel.getText().toString().equals("사용 가능") && pwSameCheckLabel.getText().toString().equals("일치"))){
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                }else if(!checkBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject signupObj = new JSONObject();
                    try {
                        signupObj.put("email", inputId.getText().toString());
                        signupObj.put("password", inputPw.getText().toString());
                        signupObj.put("id",inputId.getText().toString());
                        mongoLabClient = new MongoLabClient(API_KEY, DATABASE, COLLECTION_USER);
                        mongoLabClient.insert(signupObj.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    final Intent intent = new Intent(getApplication(), LoginActivity.class);
                    //로그인 성공시 메인화면으로 이동하도록 하기.
                    startActivity(intent);
                    finish();
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

    public void setIdConfirmed(boolean idConfirmed) {
        isIdConfirmed = idConfirmed;
    }

    public void setIdDuplicated(boolean idDuplicated) {
        isIdDuplicated = idDuplicated;
    }
}
