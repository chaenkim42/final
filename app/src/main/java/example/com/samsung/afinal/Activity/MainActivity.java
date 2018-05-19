package example.com.samsung.afinal.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONObject;

//import example.com.samsung.afinal.Adapter.JavaScriptInterface;
import example.com.samsung.afinal.Classes.MongoLabClient;
import example.com.samsung.afinal.Fragment.FavoriteFragment;
import example.com.samsung.afinal.Fragment.PersonalInfoFragment;
import example.com.samsung.afinal.Handler.BackPressCloseHandler;
import example.com.samsung.afinal.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BackPressCloseHandler backPress;

    // Information to access to mLab
    private String API_KEY = "XhgaoR68m-lW9uUX1WGMO9tOmd0TPvlQ";
    private String DATABASE = "dbchtest";
    private String COLLECTION = "test";
    private MongoLabClient mongoLabClient;
    private JSONObject jsonObject,jsonObject2,jsonObject3;


//    private FrameLayout mainFrameLayout;
    private ScrollView mainScrollView;
    public static RelativeLayout fragmentContainer;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TextView mypageTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backPress = new BackPressCloseHandler(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.list,  null);
//        ActionBar a = getSupportActionBar();
//        a.setIcon(drawable);

//        mainFrameLayout = findViewById(R.id.main_framelayout);
        mainScrollView = findViewById(R.id.main_scrollview);
        mainScrollView.setVisibility(View.VISIBLE);

        fragmentManager = getFragmentManager();

        fragmentContainer = findViewById(R.id.fragment_container);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getFragmentManager().getBackStackEntryCount() > 1){
            getFragmentManager().popBackStack();
        }else if(getFragmentManager().getBackStackEntryCount() == 1){
            getFragmentManager().popBackStack();
            mainScrollView.setVisibility(View.VISIBLE);
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
        // begin new transaction
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.addToBackStack(null).add(R.id.main_framelayout, favoriteFragment).commit();
//        fragmentTransaction.addToBackStack(null).add(R.id.main_framelayout, favoriteFragment).commit();

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.nav_home) {
            // 홈으로 돌아오면 스택 모두 제거하기
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                fragmentManager.popBackStack();
            }

            mainScrollView.setVisibility(View.VISIBLE);
            fragmentContainer.setVisibility(View.GONE);

        } else if (id == R.id.nav_favorite) {
            // begin new transaction
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null).replace(R.id.fragment_container, new FavoriteFragment()).commit();

            mainScrollView.setVisibility(View.INVISIBLE);
            fragmentContainer.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_upload) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_mypage) {
            // begin new transaction
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null).replace(R.id.fragment_container, new PersonalInfoFragment()).commit();

            mainScrollView.setVisibility(View.INVISIBLE);
            fragmentContainer.setVisibility(View.VISIBLE);
        }else {
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
