package example.com.samsung.afinal.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import example.com.samsung.afinal.Adapter.ContentsViewPagerAdapter;
import example.com.samsung.afinal.Classes.MongoLabClient;
import example.com.samsung.afinal.Interface.OnItemClickListener;
import example.com.samsung.afinal.R;

import static example.com.samsung.afinal.Activity.MainActivity.API_KEY;
import static example.com.samsung.afinal.Activity.MainActivity.COLLECTION_FOLDER;
import static example.com.samsung.afinal.Activity.MainActivity.DATABASE;
import static example.com.samsung.afinal.Activity.MainActivity.allRecipes;
import static example.com.samsung.afinal.Activity.MainActivity.mongoLabClient;

public class ViewpagerFragment extends Fragment{

    private int position;
    public int imagesArray[][];
    public String pagesTextArray[][];
    public ViewPager viewPager;
    ContentsViewPagerAdapter contentsViewPagerAdapter;
    OnItemClickListener onItemClickListener;

    public JSONObject eachRecipe;
    public ArrayList<ArrayList<Integer>> images = new ArrayList<ArrayList<Integer>>();
    public ArrayList<ArrayList<String>> pagesOfAll = new ArrayList<ArrayList<String>>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_contents, container, false);

        int[] drawables = {R.drawable.food_tomato_pasta,R.drawable.food_potato,R.drawable.food_kimchi, R.drawable.food_salad};
//        JSONArray pageArray;
//        try {
//            mongoLabClient = new MongoLabClient(API_KEY, DATABASE, "pages");
//            String mongoResultString = mongoLabClient.find();
//            pageArray = new JSONArray(mongoResultString);
//
//            int idx = 0;
//            while(idx<allRecipes.length()){
//                eachRecipe = new JSONObject(allRecipes.getString(idx));
////                for(int j=0; j < eachRecipe.length(); j++){
//                    JSONArray pages = new JSONArray(eachRecipe.getString("pages"));
//
//                    for(int k=0; k<pages.length(); k++){
//                        JSONObject page = new JSONObject(pages.getString(k));
//                        String pageOid = page.getString("$oid");
////                        if(k==0)
//                        {
//                            Log.e("k", k + "");
//                            Log.e("page", page + "");
//                            Log.e("pageOid", pageOid + "");
//                        }
//
//                        JSONObject p = new JSONObject(pageArray.getString(pageArray.length()));
//                        for(int l=0; l<pageArray.length(); l++){
//                            if(pageArray.getString(l).contains(pageOid)) {
//                                ArrayList<String> pp = new ArrayList<>();
//                                while(pp.size() < pages.length()){
//                                    pp.add(p.getString("text"));
//                                    Log.e("pp",pp+"");
//                                    if(pp.size() == page.length()) {
//                                        pagesOfAll.add(pp);
//                                        pp = new ArrayList<>();
//                                        Log.e("pp and pageofAll",pp+""+pagesOfAll+"");
////                                        Log.e("l is",l+"");
//                                        break;
//                                    }
//                                }
//                                Log.e("M-p.getString(\"text\")", l+" "+p.getString("text"));
////                                Log.e("l", l + "");
//                                if(l ==pageArray.length()-1) {
//                                }
//                            }
//                        }
//
//                    }
////              }
////                    Log.e("eee eachRecipe.get(\"pages\")",eachRecipe.getString("pages")+"");
//
//                ArrayList<Integer> tmp = new ArrayList<Integer>();
//                images.add(tmp);
//                if(idx<3){
//                    images.get(idx).add(drawables[idx]);
//                }else{
//                    images.get(idx).add(drawables[idx]);
//                }
//                idx++;
//            }
//
//
//            }catch (JSONException e) {
//               e.printStackTrace();
//            }

        ArrayList<String> p = new ArrayList<String>();
        p.add("마늘은 편썰고 양파는 잘게 다지고 베이컨도 잘게 썰어주세요.");
        p.add("올리브 오일 3 티스푼을 두른 팬에 약불에서 마늘향을 내며 느긋하게 볶다가 양파를 넣어 마저 볶아주세요.");
        p.add("베이컨과 후추 약간을 넣고 볶아주세요.");
        p.add("토마토 소스, 바질, 버터, 물을 넣고 10분정도 중약불에서 달달 볶아주세요. 이때 재료들이 충분히 어우러지도록 뭉근하게 볶아주세요.");
        p.add("스파게티 면은 끓는 물에 소금 약간을 넣고 약간 빳빳하게 삶아주세요.");
        p.add("파스타 면과 면수를 소스에 넣고 잘 어우러지도록 2,3분간 볶아주세요.");

        ArrayList<String> j = new ArrayList<String>();
        j.add("감자전의 느끼함을 잡아주기 위해 청양고추를 사용합니다. 양파는 단맛과 감칠맛을 내기 위한 조미료로 사용했으나, 많이 사용시 수분이 생겨 질척해지니 조금만 넣으세요.");
        j.add("큼직하게 썰은 감자를 곱게 갈아주세요.");
        j.add("곱게 갈은 감자를 체에 받쳐 물기를 빼주세요.");
        j.add("감자 건더기에서 거른 물에서 전분을 남겨주세요.");
        j.add("감자 건더기와 전분을 섞어주세요.");
        j.add("곱게 다진 청양고추와 양파를 섞어주세요.");

        ArrayList<String> t = new ArrayList<String>();
        t.add("어묵은 사각형으로 잘라주시고 대파는 어슷썰기 해주세요.");
        t.add("물 500~800ml 정도에 고추장을 큰 한스푼 넣어 풀어주세요.");
        t.add("고추장을 풀어준 뒤에 고춧가루도 큰 한스푼 넣어주세요.그리고 설탕, 물엿, 다시다도 약간 넣어주세요. 재료의 비율은 고추장:고춧가루:설탕:물엿 = 1:1:0.3:0.2 정도로 생각해주세요.");
        t.add("떡을 넣고 오일을 약간 넣어주세요.");
        t.add("떡이 말랑해지면 썰어두었던 어묵을 넣어 섞어주시고 대파도 넣어 한소끔 끓여주세요.");

        pagesOfAll.add(p);
        pagesOfAll.add(j);
        pagesOfAll.add(t);

        ArrayList<Integer> img = new ArrayList<Integer>();
        for(int i=0; i<6; i++){
            img.add(drawables[0]);
        }
        images.add(img);


        ArrayList<Integer> img2 = new ArrayList<Integer>();
        for(int i=0; i<6; i++){
            img.add(drawables[1]);
        }
        images.add(img2);

        ArrayList<Integer> img3 = new ArrayList<Integer>();
        for(int i=0; i<5; i++){
            img.add(drawables[2]);
        }
        images.add(img3);

        Log.e("pagesOfAll",pagesOfAll+"");
        viewPager = view.findViewById(R.id.contentsPages);
        contentsViewPagerAdapter = new ContentsViewPagerAdapter(getFragmentManager());
        contentsViewPagerAdapter.setData(images.get(position));
        contentsViewPagerAdapter.setDataText(pagesOfAll.get(position));
        viewPager.setAdapter(contentsViewPagerAdapter);

        return view;
    }


    public void setPosition(int position){this.position = position;}

}
