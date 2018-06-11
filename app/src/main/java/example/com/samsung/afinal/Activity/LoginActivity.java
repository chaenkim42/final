package example.com.samsung.afinal.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import example.com.samsung.afinal.Classes.MongoLabClient;
import example.com.samsung.afinal.Handler.BackPressCloseHandler;
import example.com.samsung.afinal.R;

public class LoginActivity extends AppCompatActivity {

    BackPressCloseHandler backPress;

    private String API_KEY = "XhgaoR68m-lW9uUX1WGMO9tOmd0TPvlQ";
    private String DATABASE = "dbchtest";
    private String COLLECTION_USER = "users";
    private MongoLabClient mongoLabClient;
    private JSONObject jsonObject,jsonObject2,jsonObject3;

    private EditText inputId;
    private EditText inputPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        backPress = new BackPressCloseHandler(this);

        inputId = findViewById(R.id.login_id);
        inputPw = findViewById(R.id.login_pw);




    }
    public void signupBtnOnClick(View view){
        final Intent intent =  new Intent(this, SignupActivity.class);
        startActivity(intent);
        //이부분에 기존에 로그인 폼에 입력했던 값들 다 비워주는 것도 추가하기
    }

    public void loginBtnOnClick(View view){
        String id = inputId.getText().toString();
        String pw = inputPw.getText().toString();
        if(id.length() == 0 || pw.length() == 0){
            Toast.makeText(this, "모든 정보를 입력하세요.", Toast.LENGTH_SHORT).show();
        }else {
            JSONObject loginInfo = new JSONObject();
            try {
                loginInfo.put("email", id);
                loginInfo.put("password", pw);
                mongoLabClient = new MongoLabClient(API_KEY, DATABASE, COLLECTION_USER);
                String mongoResultString = mongoLabClient.findOne(loginInfo.toString());
                BasicDBObject userObject = new BasicDBObject();
                userObject = (BasicDBObject) JSON.parse(mongoResultString);
                Log.e("mongo user", userObject + "");
                Log.e("mongoResultString",mongoResultString);
                if (mongoResultString.contains("$oid")) {
                    final Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("loginUserInfo", mongoResultString);
                    //로그인 성공시 메인화면으로 이동하도록 하기.
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
    @Override
    public void onBackPressed() {
        backPress.onBackPressed();
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }
}
