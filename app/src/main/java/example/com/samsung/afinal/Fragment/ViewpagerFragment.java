package example.com.samsung.afinal.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.samsung.afinal.Adapter.ContentsViewPagerAdapter;
import example.com.samsung.afinal.Interface.OnItemClickListener;
import example.com.samsung.afinal.R;

public class ViewpagerFragment extends Fragment{

    private int position;
    public int images[][];
    public ViewPager viewPager;
    ContentsViewPagerAdapter contentsViewPagerAdapter;
    OnItemClickListener onItemClickListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_contents, container, false);

        images = new int[][]{{R.drawable.food_cream_pasta, R.drawable.food_kimchi, R.drawable.food_salad},
                {R.drawable.food_tomato_pasta, R.drawable.food_tomato_pasta, R.drawable.food_potato},
                {R.drawable.app_icon, R.drawable.food_potato, R.drawable.food_salad}};

        viewPager = view.findViewById(R.id.contentsPages);
        contentsViewPagerAdapter = new ContentsViewPagerAdapter(getFragmentManager());
        contentsViewPagerAdapter.setData(images[position]);
        viewPager.setAdapter(contentsViewPagerAdapter);

        return view;
    }

    public void getPosition(int position){this.position = position;}

}
