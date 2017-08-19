package craftosaka.syukupili.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.util.sql.MySQLiteOpenHelper;

import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.TABLE_NAME;

/**
 * Created by yocchi on 2017/08/19.
 */

public class SQLiteDateManager {

    SQLiteDatabase sqLiteDatabase;
    public MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(App.getAppContext());


    private static SQLiteDateManager dateManager = new SQLiteDateManager();

    private SQLiteDateManager() {
    }

    public static SQLiteDateManager getInstance() {
        return dateManager;
    }

    public void init() {

        //DBを削除する
        //App.getAppContext().deleteDatabase(DB);

        //App.getAppContextでアプリのコンテキストにアクセス
//        mySQLiteOpenHelper = new MySQLiteOpenHelper(App.getAppContext());
        //読み込みモードで開く
//        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();


    }


    //    データベースにアクセスして課題を取得
    public List<KadListItem> getKadDate() {

        //読み込みモードで開く
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //返す用の値を作成
        List<KadListItem> kadListItems = new ArrayList<>();
        int i = 0;
        while (cursor.moveToFirst()) {
            Log.d("test", "" + i);
            i++;
        }

        return kadListItems;
    }

    public void deleteDateBase() {

    }

}
