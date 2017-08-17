package craftosaka.syukupili.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import craftosaka.syukupili.ui.activity.MenuActivity;

/**
 * Created by yocchi on 2017/08/16.
 */

public class BaseFragment extends Fragment{

    /**
     * スワイプ検出に使う変数なのでスワイプ処理を取り入れる際はこれらもコピーしてください。
     */
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    /**
     * caraftO2からの移植テスト。KeyDownEventをフラグメント毎に設定できる。
     * フラグメント毎に処理を分ける場合はそれぞれに以下のコードを書くか、
     * あるいはsetOnkeyDown()に各フラグメントから一意の引数を渡して呼び出し元によって処理を分けるか？
     */
    public void setOnKeyDown(){
        ((MenuActivity) getActivity()).setMethod_onKeyDown(new MenuActivity.OnKeyDownListener(){
            @Override
            public boolean onKeyDown(int keyCode, KeyEvent event) {
                if(keyCode == android.view.KeyEvent.KEYCODE_BACK) {
                    ((MenuActivity) getActivity()).setDefaultKeyDownAction();
                }
                return false;
            }
        });
    }


    /**
     * 左右スワイプ検出時の処理を変更するコードです。
     * 使用例はCalendarFragmentを見てください。
     */
//    public void setOnFling(){
//        ((MenuActivity)getActivity()).setMethod_onFling(new MenuActivity.OriginalSimpleOnGestureListener(){
//            @Override
//            public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
//                //ここにスワイプ処理を書けばおｋ
//                try {
//                    if (Math.abs(event1.getY() - event2.getY()) > SWIPE_MAX_OFF_PATH) {
//                        return false;
//                    }
//                    if (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                        //左スワイプ処理
//                    } else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                        //右スワイプ処理
//                    }
//                }catch (Exception e){
////                    nothing
//                }
//                return false;
//            }
//        });
//    }


}
