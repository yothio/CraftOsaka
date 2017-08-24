package craftosaka.syukupili.util;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.List;

import craftosaka.syukupili.model.User;

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
        if (editText == null) {
            return true;
        }
        if (!TextUtils.isEmpty(editText.getText().toString())) {
            return false;
        } else {
            editText.setError("入力されていません");
            return true;
        }
    }


    public static boolean isEqualsEditText(EditText ed1, EditText ed2) {
        if (ed1 == null || ed2 == null) {
            return false;
        } else if (ed1.getText().toString().equals(ed2.getText().toString())) {
            return true;
        } else {
            ed2.setError("パスワードが間違っています");
            return false;
        }
    }

    public static boolean checkEditTextLength(EditText editText) {
        if (editText.getText().length() < 3) {
            editText.setError("設定可能なパスワードは3文字以上です");
            return false;
        } else {
            return true;
        }
    }

    //  存在するならtrue、存在しないならfalse
    public static User existAccount(String name, String pass) {
        List<User> list = PrefUtil.getUserList();

        if (list.size() == 0) {
            return null;
        }

        for (User user : list) {

            if (user.getName().equals(name) && user.getPassword().equals(pass)) {
                return user;
            }
        }
        return null;

    }

    public static User searchUser(int id){
        List<User> list = PrefUtil.getUserList();
        if (list.size() == 0) {
            return null;
        }
        for (User user : list) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    /**
     * 文字列が空またはnullでないか
     * @param text
     * @return
     */
    public static boolean checkNullChar(String text){
        if(text != null) {
            if (!text.equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * String をintに変換可能か
     * @param text
     * @return
     */
    public static boolean stringToInteger(String text){
        try{
            Integer.parseInt(text);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 0000/00/00形式のテキストから"/"をなくす
     */
    public static String convertDate(String text){
        String txtDate = "";
        if(text.length() == 10) {
            txtDate = text.substring(0, 4) + text.substring(5, 7) + text.substring(8);
        }
        return txtDate;
    }
}
