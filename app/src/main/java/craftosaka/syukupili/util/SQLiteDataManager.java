package craftosaka.syukupili.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.util.sql.MySQLiteOpenHelper;

import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.CHILD_ID_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.CHILD_NAME_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.END_DATE_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.KAD_CONTENT_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.KAD_ID_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.KAD_NAME_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.POINT_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.PROGRESS_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.SETTING_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.START_DATE_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.MySQLiteOpenHelper.TABLE_NAME;

/**
 * Created by yocchi on 2017/08/19.
 */

public class SQLiteDataManager {

    SQLiteDatabase sqLiteDatabase;
    public MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(App.getAppContext());
    private static SQLiteDataManager dateManager = new SQLiteDataManager();

    private SQLiteDataManager() {
    }

    public static SQLiteDataManager getInstance() {
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

    /**
     * テーブルがあるか調べる
     * @return true : exist , false : not exsit
     */
    private boolean checkTable(){

        boolean exist = false;
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        String query = "SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='"+ TABLE_NAME+"';";
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        String result = c.getString(0);

        Log.d("result", result);


        return exist;
    }

    public void updateKadDate(List<KadListItem> kadListItems){
        //読み込みモードで開く
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToLast();
        KadListItem kadListItem = new KadListItem();

        kadListItem.setKadId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KAD_ID_COLUMN_NAME))));
        kadListItem.setKadName(cursor.getString(cursor.getColumnIndex(KAD_NAME_COLUMN_NAME)));
        kadListItem.setKadContent(cursor.getString(cursor.getColumnIndex(KAD_CONTENT_COLUMN_NAME)));
        kadListItem.setChildId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CHILD_ID_COLUMN_NAME))));
        kadListItem.setChildName(cursor.getString(cursor.getColumnIndex(CHILD_NAME_COLUMN_NAME)));
        kadListItem.setPoint(Integer.parseInt(cursor.getString(cursor.getColumnIndex(POINT_COLUMN_NAME))));
        kadListItem.setStartDate(Integer.parseInt(cursor.getString(cursor.getColumnIndex(START_DATE_COLUMN_NAME))));
        kadListItem.setEndDate(Integer.parseInt(cursor.getString(cursor.getColumnIndex(END_DATE_COLUMN_NAME))));
        kadListItem.setProgressFrag(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROGRESS_COLUMN_NAME))));
        kadListItem.setSettingFrag(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(SETTING_COLUMN_NAME))));

        kadListItems.add(kadListItem);
    }

    //    データベースにアクセスして課題を取得
    public List<KadListItem> getKadData(List<KadListItem> kadListItems) {

        //読み込みモードで開く
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //返す用の値を作成

        int i = 0;
        boolean flag = cursor.moveToFirst();
        while (flag) {
            KadListItem kadListItem = new KadListItem();
            kadListItem.setKadId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KAD_ID_COLUMN_NAME))));
            kadListItem.setKadName(cursor.getString(cursor.getColumnIndex(KAD_NAME_COLUMN_NAME)));
            kadListItem.setKadContent(cursor.getString(cursor.getColumnIndex(KAD_CONTENT_COLUMN_NAME)));
            kadListItem.setChildId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CHILD_ID_COLUMN_NAME))));
            kadListItem.setChildName(cursor.getString(cursor.getColumnIndex(CHILD_NAME_COLUMN_NAME)));
            kadListItem.setPoint(Integer.parseInt(cursor.getString(cursor.getColumnIndex(POINT_COLUMN_NAME))));
            kadListItem.setStartDate(Integer.parseInt(cursor.getString(cursor.getColumnIndex(START_DATE_COLUMN_NAME))));
            kadListItem.setEndDate(Integer.parseInt(cursor.getString(cursor.getColumnIndex(END_DATE_COLUMN_NAME))));
            kadListItem.setProgressFrag(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROGRESS_COLUMN_NAME))));
            kadListItem.setSettingFrag(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(SETTING_COLUMN_NAME))));
            kadListItems.add(kadListItem);
            Log.d("test", "" + i);
            i++;
            flag = cursor.moveToNext();
        }

        return kadListItems;
    }

    public void deleteDataBase() {
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("delete  from " + TABLE_NAME);
    }
//" ( kad_id integer primary key, " +
//        "kad_name text not null , " +
//        "kad_content text not null , " +
//        "child_id text not null , " +
//        "child_name text not null , " +
//        "point text not null , " +
//        "start_date text not null , " +
//        "progress_frag text not null , " +
//        "setting_frag text not null);";


    public void insertDataBase(int number){

        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();

        sqLiteDatabase.execSQL("insert into " + TABLE_NAME +
                " Values(" + String.valueOf(number) + ", " +
                "'" + "test" + String.valueOf(number) + "Kname" + "', " +
                "'" + "test" + String.valueOf(number) + "Kcontent" + "', " +
                "'" + "1" + "', " +
                "'" + "test" + String.valueOf(number) + "Cname" + "', " +
                "'" + "20" + "', " +
                "'" + "" + String.valueOf(19960202 + number) + "', " +
                "'" + "" + String.valueOf(11111111 + number) + "" + "', " +
                "'" + "" + String.valueOf(number) + "" + "', " +
                "'" +  "" + String.valueOf(number) + ""+ "')");

        sqLiteDatabase.close();
    }

}
