package craftosaka.syukupili.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import craftosaka.syukupili.ui.activity.MenuActivity;
import craftosaka.syukupili.ui.activity.StartActivity;

/**
 * Created by yocchi on 2017/08/16.
 */

public class BaseFragment extends Fragment{

    /**
     * スワイプ検出に使う変数。
     */
    public static final int SWIPE_MAX_OFF_PATH = 250;
    public static final int SWIPE_MIN_DISTANCE = 120;
    public static final int SWIPE_THRESHOLD_VELOCITY = 200;

    /**
     * KeyDownEventをフラグメント毎に設定できる。
     * フラグメント毎に処理を分ける場合はそれぞれに以下のコードを書くか、
     * あるいはsetOnkeyDown()に各フラグメントから一意の引数を渡して呼び出し元によって処理を分けるか？
     */
    public void setOnKeyDown(){
        ((MenuActivity) getActivity()).setMethod_onKeyDown(new MenuActivity.OnKeyDownListener(){
            @Override
            public boolean onKeyDown(int keyCode, KeyEvent event) {
                Toast.makeText(getActivity(),"BackKey Down",Toast.LENGTH_SHORT).show();
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        Intent intent = new Intent(getActivity(),StartActivity.class);
                        startActivity(intent);
                        // Androidの戻るボタンが押された時
                }
                return false;
            }
        });
    }


    /**
     * 左右スワイプ検出時の処理を変更するコードです。
     * 使用例はCalendarFragmentを見てください。
     */
    public void setOnFling(){
        ((MenuActivity)getActivity()).setMethod_onFling(new MenuActivity.OriginalSimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
                //デフォルト的なスワイプ処理
                try {
                    if (Math.abs(event1.getY() - event2.getY()) > SWIPE_MAX_OFF_PATH) {
                        return false;
                    }
                    if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //左スワイプ処理
                    } else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        //右スワイプ処理
                    }
                }catch (Exception e){
//                    nothing
                }
                return false;
            }
        });
    }


}
