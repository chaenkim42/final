package example.com.samsung.afinal.Adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import example.com.samsung.afinal.Fragment.ContentsFragment;

public class ContentsViewPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Integer> images = new ArrayList<Integer>();
    ArrayList<String> pagesText = new ArrayList<String>();

    public ContentsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(ArrayList<Integer> images){this.images = images;}
    public void setDataText(ArrayList<String> pagesText){this.pagesText = pagesText;}

    @Override
    public Fragment getItem(int position) {
        return new ContentsFragment().newInstance(images.get(position), pagesText.get(position));
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
