package craftosaka.syukupili.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yocchi on 2017/08/16.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragmentList;


    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.mFragmentList = fragmentList;
    }
}
