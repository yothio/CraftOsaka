package craftosaka.syukupili.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import craftosaka.syukupili.R;
import craftosaka.syukupili.ui.adapter.FragmentViewPagerAdapter;
import craftosaka.syukupili.ui.fragment.CalenderFragment;
import craftosaka.syukupili.ui.fragment.KadListFragment;
import craftosaka.syukupili.ui.fragment.PointExchangeFragment;
import craftosaka.syukupili.ui.fragment.SettingFragment;
import craftosaka.syukupili.util.Data;
import craftosaka.syukupili.util.Util;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * Created by yocchi on 2017/08/15.
 */

public class MenuActivity extends AppCompatActivity {
    BottomNavigation tabNavigation;
    private ViewPager viewPager;

    KadListFragment kadListFragment;
    CalenderFragment calenderFragment;
    PointExchangeFragment pointExchangeFragment;
    SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        gestureDetector = new GestureDetector(this,onGestureListener);

        //Fragmentを簡易に切り替えるためのViewPagerとそのadapter
        viewPager = (ViewPager) findViewById(R.id.fragment_viewpager);
        FragmentViewPagerAdapter pagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        //リストに格納して、adapterに譲渡
        // new Class名にせずに、Instancenewにしたのは初期化時にこちらから値を渡す可能性があるため
        // 値を変更してもらっても構わない
        List<Fragment> list = new ArrayList<>();
        list.add(kadListFragment = new KadListFragment().newInstance());
        list.add(calenderFragment = new CalenderFragment().newInstance());
        list.add(pointExchangeFragment = new PointExchangeFragment().newInstance());
        list.add(settingFragment = new SettingFragment().newInstance());
        //adapterにfragmentのリストを渡す
        pagerAdapter.setFragmentList(list);

        //下タブのオブジェクトとitemを選択した時の動作
        tabNavigation = (BottomNavigation) findViewById(R.id.BottomNavigation);
        tabNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {

            //今のitemから別のitemを選択した時  Ex)fromカレンダー toポイント
            @Override
            public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {
                Log.d("MenuActivity", "Select i : " + i + " i1 : " + i1);
                // あらかじめadapterで設定したfragmentに切り替える
                viewPager.setCurrentItem(i1);
                switch (i1){
                    case 0:
                        kadListFragment.setFunction();
                        break;
                    case 1:
                        calenderFragment.setFunction();
                        break;
                    case 2:
                        pointExchangeFragment.setFunction();
                        break;
                    case 3:
                        Toast.makeText(MenuActivity.this, "Data.getIstance().getNowUser().getPoint():" + Data.getInstance().getNowUser().getPoint(), Toast.LENGTH_SHORT).show();
                        settingFragment.setFunction();
                        break;
                }
            }
            //今と同じitemを選択した時          Ex)fromカレンダー toカレンダー
            @Override
            public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {
                Log.d("MenuActivity", "Reselect i : " + i + " i1 : " + i1);

            }
        });
        //adapterを設定
        viewPager.setAdapter(pagerAdapter);

    }


    /**
     * 以下、KeyDownに関する処理
     */
    private OnKeyDownListener onKeyDownListener;

    public interface OnKeyDownListener {
        boolean onKeyDown(int keyCode, KeyEvent event);
    }

    //BaseFragmentでデフォルト処理を行っているので使用しません。
//    public void setDefaultKeyDownAction() {
//        setMethod_onKeyDown(new OnKeyDownListener() {
//            @Override
//            public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//                switch (keyCode) {
//                    case KeyEvent.KEYCODE_BACK:
//                        Intent intent = new Intent(getApplicationContext(),StartActivity.class);
//                        startActivity(intent);
//                         //Androidの戻るボタンが押された時
//                }
//                return true;
//            }
//        });
//    }

    public void setMethod_onKeyDown(OnKeyDownListener listener) {
        this.onKeyDownListener = listener;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!onKeyDownListener.onKeyDown(keyCode, event)) return false;
        return super.onKeyDown(keyCode, event);
    }
    /**
     * KeyDown処理ここまで
     */

    /**
     * 以下、KeyDown処理を参考にして作ったスワイプ検出
     */
    private GestureDetector gestureDetector;
    private OriginalSimpleOnGestureListener originalListener;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return gestureDetector.onTouchEvent(event);
    }

    private GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if( !originalListener.onFling(e1,e2,velocityX,velocityY)) return false;
            return super.onFling(e1,e2,velocityX,velocityY);
        }
    };

    public void setMethod_onFling(OriginalSimpleOnGestureListener onGestureListener) {
        this.originalListener = onGestureListener;
    }
    public interface OriginalSimpleOnGestureListener {
        boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
    }
    /**
     * スワイプ処理ここまで
     */

    /**
     * カレンダーの日付が選択された時の処理
     * @param v　
     */
    public void dayTextClick(View v){
        //CalendarFragmentに処理を投げる
        calenderFragment.selectedDay(v);
    }

}
