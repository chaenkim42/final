package example.com.samsung.afinal.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.JSON;

import org.bson.BasicBSONObject;
import org.bson.types.BasicBSONList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import example.com.samsung.afinal.Adapter.JavaScriptInterface;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import example.com.samsung.afinal.Adapter.Adapter_Main;
import example.com.samsung.afinal.Classes.MongoLabClient;
import example.com.samsung.afinal.Classes.data_Main;
import example.com.samsung.afinal.Fragment.FavoriteFragment;
import example.com.samsung.afinal.Fragment.PersonalInfoFragment;
import example.com.samsung.afinal.Fragment.SettingFragment;
import example.com.samsung.afinal.Fragment.UploadFragment;
import example.com.samsung.afinal.Fragment.ViewpagerFragment;
import example.com.samsung.afinal.Handler.BackPressCloseHandler;
import example.com.samsung.afinal.Interface.OnItemClickListener;
import example.com.samsung.afinal.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BackPressCloseHandler backPress;

    public static JSONObject USER_SESSION;
    public static ArrayList<String> recipes;
    public static JSONArray allRecipes;

    // Information to access to mLab
    public static final String API_KEY = "XhgaoR68m-lW9uUX1WGMO9tOmd0TPvlQ";
    public static final String DATABASE = "dbchtest";
    public static final String COLLECTION_RECIPE = "recipes";
    public static final String COLLECTION_FOLDER = "folders";
    public static MongoLabClient mongoLabClient;


//    private FrameLayout mainFrameLayout;
//    private ScrollView mainScrollView;
    public static RelativeLayout fragmentContainer;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TextView mypageTV;


    //main RecyclerView
    public ArrayList<data_Main> items;
    public LinearLayoutManager linearLayoutManager;
    public Adapter_Main adapter_main;
    public RecyclerView contentsContainer;

    //contents ViewPager

    ViewpagerFragment viewpagerFragment;
    OnItemClickListener onItemClickListener;
    OnItemClickListener onStarClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(items.get(position).getIsClicked() == R.drawable.star)
                items.get(position).setClicked(R.drawable.star_clicked);
            else
                items.get(position).setClicked(R.drawable.star);
            adapter_main.notifyDataSetChanged();

        }
    };


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

        Intent intent = getIntent();
        String loginUserInfo = intent.getStringExtra("loginUserInfo");
//        Log.e("mongo login", loginUserInfo);
        loginUserInfo = loginUserInfo.replace("\"", "\"");
        try {
            USER_SESSION = new JSONObject(loginUserInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.e("mongo json login", USER_SESSION+"");


        try {
            final String user = USER_SESSION.getString("email").split("@")[0];
            View headerView = navigationView.getHeaderView(0);
            TextView navUsername = (TextView) headerView.findViewById(R.id.nav_header_userid);
            navUsername.setText(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        recipes = new ArrayList<String>();
        mongoLabClient = new MongoLabClient(API_KEY, DATABASE, COLLECTION_RECIPE);
        String[] split = mongoLabClient.find().split("\"title\"");
        for(int i=0; i<split.length-1; i++){
            String string = split[i+1].split("\"")[1];
            recipes.add(string);
        }
//        Log.e("recipes",recipes.get(0)+","+recipes.get(2));


        try {
            allRecipes = new JSONArray();
            for (int i = 0; i < recipes.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", recipes.get(i));
                mongoLabClient = new MongoLabClient(API_KEY, DATABASE, COLLECTION_RECIPE);
                String mongoResultString = mongoLabClient.findOne(jsonObject.toString());
                JSONObject recipeObject = new JSONObject(mongoResultString);
                allRecipes.put(recipeObject);
            }
//            Log.e("where is it?", new JSONObject(allRecipes.get(0).toString()).getString("title")+ "");
        } catch (JSONException e) {
            Log.e("JSONException", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        fragmentManager = getFragmentManager();

        fragmentContainer = findViewById(R.id.fragment_container);

        //==================================================================================================
        //ViewPager 즉 하나의 컨텐츠를 클릭했을때를 의미합니다. 그럴때의 작업을 구현한 부분입니다.



        onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //뷰페이저 실행 부분[fragment에 viewpager view를 담았습니다.]
                viewpagerFragment = new ViewpagerFragment();
                viewpagerFragment.setPosition(position);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null).setCustomAnimations(R.animator.enter_anim_alpha, R.animator.exit_anim_alpha).
                        replace(R.id.fragment_container, viewpagerFragment).commit();

            }
        };
        //==================================================================================================




        //main content 부분
        items = new ArrayList<>();
        for(int i=0; i<recipes.size(); i++){
            //TODO: context 부분 db 수정 or 없애기
            if(i==0) {
                items.add(new data_Main(recipes.get(i), R.drawable.food_tomato_pasta, "", R.drawable.star));
            }else if(i==1){
                items.add(new data_Main(recipes.get(i), R.drawable.food_potato, "", R.drawable.star));
            }else if(i==2){
                items.add(new data_Main(recipes.get(i), R.drawable.food_kimchi, "", R.drawable.star));
            }else{
                items.add(new data_Main(recipes.get(i), R.drawable.food_salad, "", R.drawable.star));
            }
        }
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        contentsContainer = findViewById(R.id.contents_container);
        contentsContainer.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
//        contentsContainer.setHasFixedSize(true);
        contentsContainer.setLayoutManager(linearLayoutManager);
        adapter_main = new Adapter_Main();
        adapter_main.setStarListener(onStarClickListener);
        adapter_main.setListener(onItemClickListener);
        adapter_main.setData(items);
        contentsContainer.setAdapter(adapter_main);
        //==================================================================================================


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
            contentsContainer.setVisibility(View.VISIBLE);
//            mainScrollView.setVisibility(View.VISIBLE);
        } else {
            backPress.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // 메뉴 안보이게 해서 없애는 코드
        toolbar.getMenu().clear();
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.nav_home) {
            // 홈으로 돌아오면 스택 모두 제거하기
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                fragmentManager.popBackStack();
            }

            contentsContainer.setVisibility(View.VISIBLE);

            fragmentContainer.setVisibility(View.INVISIBLE);


        } else if (id == R.id.nav_favorite) {
            // begin new transaction
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null).setCustomAnimations(R.animator.enter_anim_scale,R.animator.exit_anim_scale).replace(R.id.fragment_container, new FavoriteFragment()).commit();

            contentsContainer.setVisibility(View.INVISIBLE);
            fragmentContainer.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_upload) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null).setCustomAnimations(R.animator.enter_anim_scale,R.animator.exit_anim_scale).replace(R.id.fragment_container, new UploadFragment()).commit();

            contentsContainer.setVisibility(View.INVISIBLE);
            fragmentContainer.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_setting) {
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null).setCustomAnimations(R.animator.enter_anim_scale,R.animator.exit_anim_scale).replace(R.id.fragment_container, new SettingFragment()).commit();

            contentsContainer.setVisibility(View.INVISIBLE);
            fragmentContainer.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_mypage) {
            // begin new transaction
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null).setCustomAnimations(R.animator.enter_anim_scale,R.animator.exit_anim_scale).replace(R.id.fragment_container, new PersonalInfoFragment()).commit();

            contentsContainer.setVisibility(View.INVISIBLE);
            fragmentContainer.setVisibility(View.VISIBLE);
        }else {
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
