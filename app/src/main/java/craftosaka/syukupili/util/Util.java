package craftosaka.syukupili.util;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by yocchi on 2017/08/16.
 */

public class Util {
    private static final Util ourInstance = new Util();

    public static Util getInstance() {
        return ourInstance;
    }

    private Util() {
    }


    public String correctDate(String oldDate) {
        String newDate = oldDate;
        if (oldDate.length() >= 8) {
            newDate = oldDate.substring(0, 4) + "/" + oldDate.substring(4, 6) + "/" + oldDate.substring(6, 8);
        }
        return newDate;
    }


    public static boolean isEmptyEditText(EditText editText) {
        if(editText == null){
            return true;
        }
        if(!TextUtils.isEmpty(editText.getText().toString())){
            return false;
        } else  {
            editText.setError("入力されていません");
            return true;
        }
    }
}
