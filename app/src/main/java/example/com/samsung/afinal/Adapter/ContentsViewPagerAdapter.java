package example.com.samsung.afinal.Adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import example.com.samsung.afinal.Fragment.ContentsFragment;

public class ContentsViewPagerAdapter extends FragmentStatePagerAdapter {

    int images[];

    public ContentsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(int[] images){this.images = images;}

    @Override
    public Fragment getItem(int position) {
        return new ContentsFragment().newInstance(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }
}
