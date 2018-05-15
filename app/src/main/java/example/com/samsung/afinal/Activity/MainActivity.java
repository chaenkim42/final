package example.com.samsung.afinal.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.ScrollView;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Set;

//import example.com.samsung.afinal.Adapter.JavaScriptInterface;
import example.com.samsung.afinal.Classes.MongoLabClient;
import example.com.samsung.afinal.Classes.Recipe;
import example.com.samsung.afinal.Fragment.FavoriteFragment;
import example.com.samsung.afinal.Handler.BackPressCloseHandler;
import example.com.samsung.afinal.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BackPressCloseHandler backPress;
    private List<Recipe> List1;

    private String API_KEY = "XhgaoR68m-lW9uUX1WGMO9tOmd0TPvlQ";
    private String DATABASE = "appdb";
    private String COLLECTION = "test";
    private MongoLabClient mongoLabClient;
    JSONObject jsonObject,jsonObject2,jsonObject3;



    ScrollView contentMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentMain = findViewById(R.id.container_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        backPress = new BackPressCloseHandler(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        try {
//            Block<Document> printBlock = new Block<Document>() {
//                @Override
//                public void apply(final Document document) {
//                    Log.i("eee",document.toJson());
//                }
//            };

            mongoLabClient = new MongoLabClient(API_KEY);
            Log.i("eeee mongoLabClient",mongoLabClient.getDatabaseName());
            mongoLabClient = new MongoLabClient(API_KEY);
            Log.i("eeee mongoLabClient",mongoLabClient.getCollectionName(DATABASE));

            jsonObject = new JSONObject();
            jsonObject.put("one", "data01");
            jsonObject.put("two", "jsonObject");
            jsonObject2 = new JSONObject();
            jsonObject2.put("three-one",99);
            jsonObject2.put("three-two", "BasicDBObject");
            jsonObject.put("three", jsonObject2);

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            jsonArray.put(jsonObject2);
            mongoLabClient = new MongoLabClient(API_KEY, DATABASE, COLLECTION);
            mongoLabClient.insert(jsonArray);


        }catch(JSONException e){
            Log.e("eeeee error", e.toString());
        }catch(Exception e){
            Log.e("eeeee error", e.toString());
        }
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
        FragmentManager fm;
        FragmentTransaction fragmentTransaction;

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_favorite) {
            contentMain.setVisibility(View.INVISIBLE);
            FavoriteFragment firstFragment = new FavoriteFragment();
            fm = getFragmentManager();
            fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainer, firstFragment);
            firstFragment.setArguments(getIntent().getExtras());
//            getFragmentManager().beginTransaction()
//                    .add(R.id.fragmentContainer,firstFragment).commit();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_upload) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
