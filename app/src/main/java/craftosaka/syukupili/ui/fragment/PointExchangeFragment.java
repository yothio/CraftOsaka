package craftosaka.syukupili.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import craftosaka.syukupili.R;
import craftosaka.syukupili.ui.activity.MenuActivity;

/**
 * Created by yocchi on 2017/08/16.
 */

public class PointExchangeFragment extends BaseFragment {

    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public static PointExchangeFragment newInstance() {
        PointExchangeFragment fragment = new PointExchangeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_point_layout, container, false);

        return v;
    }

    @Override
    public void setOnFling(){
        ((MenuActivity)getActivity()).setMethod_onFling(new MenuActivity.OriginalSimpleOnGestureListener(){
            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
                try {
                    if (Math.abs(event1.getY() - event2.getY()) > SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //左スワイプ
                        Toast.makeText(getContext(),"左スワイプ",Toast.LENGTH_SHORT).show();
                    } else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //右スワイプ
                        Toast.makeText(getContext(),"右スワイプ",Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e){
//                    nothing
                }
                return false;
            }
        });
    }

    /**
     * フラグメントを切り替えたときにMenuActivityから呼び出され、
     * 各フラグメント毎に設定を行う。
     */
    public void setFunction() {
        //KeyDownイベント処理を設定
        super.setOnKeyDown();

    }
}