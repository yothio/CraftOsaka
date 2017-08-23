package craftosaka.syukupili.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.model.PointListItem;
import craftosaka.syukupili.util.sql.KadSQLiteOpenHelper;
import craftosaka.syukupili.util.sql.PointSQLiteOpenHelper;

import static craftosaka.syukupili.util.sql.PointSQLiteOpenHelper.ITEM_NAME_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.PointSQLiteOpenHelper.POINT_VALUE_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.PointSQLiteOpenHelper.TABLE_NAME;


/**
 * Created by yocchi on 2017/08/23.
 */

public class PointDateManager {
    SQLiteDatabase sqLiteDatabase;
    public PointSQLiteOpenHelper mySQLiteOpenHelper = new PointSQLiteOpenHelper(App.getAppContext());
    private static PointDateManager dateManager = new PointDateManager();

    private PointDateManager() {
    }

    public static PointDateManager getInstance() {
        return dateManager;
    }

    public void init() {

        //DBを削除する
        //App.getAppContext().deleteDatabase(DB);

        //App.getAppContextでアプリのコンテキストにアクセス
//        mySQLiteOpenHelper = new KadSQLiteOpenHelper(App.getAppContext());
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

    public void updateKadDate(List<PointListItem> pointListItems){
        //読み込みモードで開く
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToLast();
        PointListItem pointListItem = new PointListItem();

        pointListItem.setPointItemName(cursor.getString(cursor.getColumnIndex(ITEM_NAME_COLUMN_NAME)));
        pointListItem.setPoint(cursor.getString(cursor.getColumnIndex(POINT_VALUE_COLUMN_NAME)));

        pointListItems.add(pointListItem);
    }

    //    データベースにアクセスして課題を取得
    public List<PointListItem> getKadData(List<PointListItem> pointListItems) {

        //読み込みモードで開く
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //返す用の値を作成

        int i = 0;
        boolean flag = cursor.moveToFirst();
        while (flag) {
            PointListItem pointListItem = new PointListItem();
            pointListItem.setPointItemName(cursor.getString(cursor.getColumnIndex(ITEM_NAME_COLUMN_NAME)));
            pointListItem.setPoint(cursor.getString(cursor.getColumnIndex(POINT_VALUE_COLUMN_NAME)));
            pointListItems.add(pointListItem);
            Log.d("test", "" + i);
            i++;
            flag = cursor.moveToNext();
        }

        return pointListItems;
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
                "'" +  "" + String.valueOf(number) + ""+ "')");

        sqLiteDatabase.close();
    }

}