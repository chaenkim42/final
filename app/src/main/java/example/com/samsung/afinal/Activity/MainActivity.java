package example.com.samsung.afinal.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

//import example.com.samsung.afinal.Adapter.JavaScriptInterface;
import example.com.samsung.afinal.Classes.MongoLabClient;
import example.com.samsung.afinal.Classes.Recipe;
import example.com.samsung.afinal.Handler.BackPressCloseHandler;
import example.com.samsung.afinal.R;
//ctrl + alt + L ==> 줄맞춤

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BackPressCloseHandler backPress;
    private List<Recipe> List1;
//    private final Handler handler = new Handler();
//    JavaScriptInterface jsi;
//    WebView webView;
    private String API_KEY = "_nn1V5xxRxq8MHOKUBN4NHzr18_4WsUq";
    private String DATABASE = "whitewhale";
    private String COLLECTION = "test";

/* mongoclient example
    private MongoLabClient mongoLabClient;
    JSONObject jsonObject, jsonObject2, jsonObject3;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        backPress = new BackPressCloseHandler(this);
//        jsi = new JavaScriptInterface(this, webView, handler);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.addJavascriptInterface(new JavaScriptInterface(), "hh");


/* mongolab client 사용 exmple
        //Check Databases and Collections
        mongoLabClient = new MongoLabClient(API_KEY);
        mongoLabClient.getDatabaseName();

        mongoLabClient = new MongoLabClient(API_KEY);
        mongoLabClient.getCollectionName(DATABASE);

        mongoLabClient = new MongoLabClient(API_KEY, DATABASE);
        mongoLabClient.getCollectionName();

        //Count Example
        try {
            jsonObject = new JSONObject();
            jsonObject.put("three", 33);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mongoLabClient = new MongoLabClient(API_KEY,DATABASE,COLLECTION);
        mongoLabClient.count();

        mongoLabClient = new MongoLabClient(API_KEY,DATABASE,COLLECTION);
        mongoLabClient.count(jsonObject);

        mongoLabClient = new MongoLabClient(API_KEY,DATABASE,COLLECTION);
        mongoLabClient.count(jsonObject.toString());

        //Find Example
        mongoLabClient = new MongoLabClient(API_KEY,DATABASE,COLLECTION);
        mongoLabClient.find();

        mongoLabClient = new MongoLabClient(API_KEY,DATABASE,COLLECTION);
        mongoLabClient.find(jsonObject);

        mongoLabClient = new MongoLabClient(API_KEY,DATABASE,COLLECTION);
        mongoLabClient.find(jsonObject.toString());

        mongoLabClient = new MongoLabClient(API_KEY,DATABASE,COLLECTION);
        mongoLabClient.findOne(jsonObject);

        mongoLabClient = new MongoLabClient(API_KEY,DATABASE,COLLECTION);
        mongoLabClient.findOne(jsonObject.toString());
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backPress.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // 상단 툴바 메뉴
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_upload) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
