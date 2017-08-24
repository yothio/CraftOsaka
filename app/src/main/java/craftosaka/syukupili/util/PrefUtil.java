package craftosaka.syukupili.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import craftosaka.syukupili.model.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by yocchi on 2017/08/23.
 */

public class PrefUtil {

    public static final String FILE_NAME = "Syukupi";
    public static final String PARENT_PASS = "parent";
    public static final String PREF_FILE = "prefFile";
    public static final String KEY_USER_LIST = "children";

    SharedPreferences preferences = App.getAppContext().getSharedPreferences("syukupi", MODE_PRIVATE);

    //-----------------------------------save

    public static void saveUserItem(User user) {
        List<User> list = getUserList();
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(0,user);
        saveUserList(list);
    }

    public static void updateUserItem(User user){
        List<User> list = getUserList();

        for (User u:list){
            if(user.getId() == u.getId()){
                u.setPoint(user.getPoint());
            }
        }
        saveUserList(list);
    }

    public static void saveUserList(List<User> list){
        save(list,KEY_USER_LIST,PREF_FILE);
    }

    public static void setParent(String pass){
        save(pass,PARENT_PASS,FILE_NAME);
    }

    public static String getParentPass(){
        return load(PARENT_PASS, FILE_NAME);
    }

    /***
     * String保存メソッド
     *
     * @param string
     * @param key
     * @param fileName
     */
    private static void save(String string, String key, String fileName) {
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, string).commit();
    }

    private static void save(int num, String key, String fileName) {
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, num).commit();
    }

    private static void save(long num ,String key,String fileName){
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(fileName,Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key,num).commit();
    }



    // LOAD -------------------------------------------------------

    public static List<User> getUserList(){
        SharedPreferences data = App.getAppContext().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        String value = data.getString(KEY_USER_LIST, "");

        List<User> list =  new Gson().fromJson(value, new TypeToken<List<User>>(){}.getType());
        if(list == null){
            list = new ArrayList<>();
        }
        return list;
    }

    private static String load(String key, String fileName, String def){
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,def);
    }
    private static String load(String key, String fileName){
        return load(key,fileName,"");
    }

    private static boolean load(String key, String fileName, boolean def){
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,def);
    }

    /**
     * オブジェクト保存メソッド
     *
     * @param key
     * @param fileName
     */
    private static void save(Object obj, String key, String fileName) {
        SharedPreferences sharedPreferences = App.getAppContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, new Gson().toJson(obj)).commit();
    }



}
