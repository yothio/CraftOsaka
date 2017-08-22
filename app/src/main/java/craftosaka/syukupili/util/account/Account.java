package craftosaka.syukupili.util.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import craftosaka.syukupili.util.Data;
import lombok.Setter;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Fukkun on 2017/08/17.
 */


//アカウント管理クラス
public class Account {

    //アカウント情報はpreferencesで管理
    SharedPreferences preferences;
    //エラーメッセージ表示用
    Context context;

    //コンストラクタ
    public Account(Context context) {
        preferences = context.getSharedPreferences("syukupi", MODE_PRIVATE);
        this.context = context;
    }

    //子供用アカウント作成　作成できればtrue  引数に名前とパスワード
    Boolean createAcount(String name, String pass) {

        Boolean result = false;

        //名前が0文字以下ならエラーメッセージとfalseを返す
        if (name.length() <= 0) {

            new AlertDialog.Builder(context)
                    .setTitle("")
                    .setMessage("なまえを入力してください")
                    .setPositiveButton("OK", null)
                    .show();
            return result;
        }

//パスワードが2文字以下ならエラーメッセージとfalseを返す
        if (pass.length() <= 2) {

            new AlertDialog.Builder(context)
                    .setTitle("")
                    .setMessage("パスワードは3文字以上にしてください")
                    .setPositiveButton("OK", null)
                    .show();
            return result;
        }

//アカウント作成しtrueを返す
        SharedPreferences.Editor editor = preferences.edit().putString(name, pass);
        editor.commit();
        result = true;
        return result;
    }

    //大人用アカウント作成　引数にパスワード
    Boolean createAcount(String pass) {

        Boolean result = false;
//パスワードが2文字以下ならエラーメッセージとfalseを返す
        if (pass.length() <= 2) {
            new AlertDialog.Builder(context)
                    .setTitle("title")
                    .setMessage("パスワードは2文字以上にしてください")
                    .setPositiveButton("OK", null)
                    .show();
            return result;
        }

//アカウント作成しtrueを返す　名前がないのでparentとして登録
        SharedPreferences.Editor editor = preferences.edit().putString("parent", pass);

        editor.commit();

        result = true;

        return result;

    }

    //子供がログインする　ログインできたらtrue 引数に名前、パスワードを
    Boolean login(String name, String pass) {

        Boolean result = false;

        //preferencesにpassがあり、あっていればログイン成功
        String userpass = preferences.getString(name, "sippai");

        if (pass.equals(userpass)) {

            //ログイン情報を保持
            SharedPreferences.Editor editor = preferences.edit().putString("loginuser", name);
            editor.commit();

            Data.getInstance().childParentFrag = false;

            result = true;

        } else {

        }

        return result;
    }

    //大人がログインする　引数にパスワードを
    public Boolean login(String pass) {

        Boolean result = false;

        //名前をキーとするため、名前を設定しない保護者はparentをキーとする
        String userpass = preferences.getString("parent", "sippai");

        if (pass.equals(userpass)) {
            //ログイン情報を保持
            SharedPreferences.Editor editor = preferences.edit().putString("loginuser", "parent");
            editor.commit();
            Data.getInstance().childParentFrag = true;
            result = true;
        } else {

        }
        return result;
    }

    //ユーザーの名前一覧を返す
    public List<String> getuserlist() {

        List<String> list = new ArrayList<String>();

        Map<String, ?> map = preferences.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {

            String key = entry.getKey();

            Object value = entry.getValue();

            //ログインしているユーザー情報はいらないのでスキップ
            if (key.equals("loginuser")) {
                break;
            }

            list.add(key);

        }

        return list;
    }

    //ログインユーザーの名前を返す 無かったら空文字列を返す
    public String loginuser(){

        String name = preferences.getString("loginuser","");

        return name;
    }

}
