package craftosaka.syukupili.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.ui.adapter.FragmentViewPagerAdapter;
import craftosaka.syukupili.ui.fragment.CalenderFragment;
import craftosaka.syukupili.ui.fragment.KadListFragment;
import craftosaka.syukupili.ui.fragment.PointExchangeFragment;
import craftosaka.syukupili.ui.fragment.SettingFragment;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * Created by yocchi on 2017/08/15.
 */

public class MenuActivity extends AppCompatActivity {
    BottomNavigation tabNavigation;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Fragmentを簡易に切り替えるためのViewPagerとそのadapter
        viewPager = (ViewPager) findViewById(R.id.fragment_viewpager);
        FragmentViewPagerAdapter pagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        //リストに格納して、adapterに譲渡
        // new Class名にせずに、Instancenewにしたのは初期化時にこちらから値を渡す可能性があるため
        // 値を変更してもらっても構わない
        List<Fragment> list = new ArrayList<>();
        list.add(new KadListFragment().newInstance());
        list.add(new CalenderFragment().newInstance());
        list.add(new PointExchangeFragment().newInstance());
        list.add(new SettingFragment().newInstance());
        //adapterにfragmentのリストを渡す
        pagerAdapter.setFragmentList(list);

        //下タブのオブジェクトとitemを選択した時の動作
        tabNavigation = (BottomNavigation) findViewById(R.id.BottomNavigation);
        tabNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {

            //今のitemから別のitemを選択した時  Ex)fromカレンダー toポイント
            @Override
            public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {
                Log.d("MenuActivity", "Select i : " + i + " i1 : " + i1);
                viewPager.setCurrentItem(i1);
            }
            //今と同じitemを選択した時          Ex)fromカレンダー toカレンダー
            @Override
            public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {
                Log.d("MenuActivity", "Reselect i : " + i + " i1 : " + i1);

            }
        });


        viewPager.setAdapter(pagerAdapter);
    }
}
