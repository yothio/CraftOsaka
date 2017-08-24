package craftosaka.syukupili.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by yoshiki on 2017/08/24.
 */

public class NotifyUtil {

    //ログインに成功したとき
    public static void loginSuccess(View v) {
        Snackbar.make(v, "ログインに成功しました", Snackbar.LENGTH_SHORT).show();
    }
    //ログインに失敗した時
    //使わない
    public static void loginFailed(View v) {
        Snackbar.make(v, "ログインに失敗しました", Snackbar.LENGTH_SHORT).show();
    }
    //登録に成功したとき
    public static void insertSuccessNotify(View v) {
        Snackbar.make(v, "登録しました", Snackbar.LENGTH_SHORT).show();

    }
    //登録に失敗したとき
    public static void insertFailedNotify(View v) {
        Snackbar.make(v, "登録に失敗しました", Snackbar.LENGTH_SHORT).show();

    }
    //更新に成功したとき
    public static void updateSuccessNotify(View v) {
        Snackbar.make(v, "更新しました", Snackbar.LENGTH_SHORT).show();

    }
    //更新に失敗した時
    public static void updateFailedNotify(View v) {
        Snackbar.make(v, "更新に失敗しました", Snackbar.LENGTH_SHORT).show();

    }
    //アイテムを交換したとき
    public static void Notify(View v) {
        Snackbar.make(v, "アイテムを交換しました", Snackbar.LENGTH_SHORT).show();

    }
    //

}
