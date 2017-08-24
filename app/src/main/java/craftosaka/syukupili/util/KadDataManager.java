package craftosaka.syukupili.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import craftosaka.syukupili.model.KadListItem;
import craftosaka.syukupili.util.sql.KadSQLiteOpenHelper;

import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.CHILD_ID_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.CHILD_NAME_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.END_DATE_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.KAD_CONTENT_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.KAD_ID_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.KAD_NAME_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.POINT_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.PROGRESS_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.SETTING_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.START_DATE_COLUMN_NAME;
import static craftosaka.syukupili.util.sql.KadSQLiteOpenHelper.TABLE_NAME;

/**
 * Created by yocchi on 2017/08/19.
 */

public class KadDataManager {

    SQLiteDatabase sqLiteDatabase;
    public KadSQLiteOpenHelper mySQLiteOpenHelper = new KadSQLiteOpenHelper(App.getAppContext());
    private static KadDataManager dateManager = new KadDataManager();

    private KadDataManager() {
    }

    public static KadDataManager getInstance() {
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
    public List<KadListItem> getKadData(List<KadListItem> kadListItems,String childId) {

        //読み込みモードで開く
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor;
        if(childId == null || childId.equals("")) {
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + PROGRESS_COLUMN_NAME + " = 0", null);
        }else{
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME +
                                            " WHERE " + CHILD_ID_COLUMN_NAME + " = " + childId +
                                            " AND " + PROGRESS_COLUMN_NAME + " = 0", null);
        }

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

    public void insertDataBase(KadListItem kadListItem){

        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();

        sqLiteDatabase.execSQL("insert into " + TABLE_NAME +
                " Values(" + String.valueOf(getDatabaseCount()) + ", " +
                "'"  + kadListItem.getKadName() + "', " +
                "'"  + kadListItem.getKadContent()  + "', " +
                "'" + kadListItem.getChildId() + "', " +
                "'" + kadListItem.getChildName() + "', " +
                "'" + kadListItem.getPoint() + "', " +
                "'" + kadListItem.getStartDate() + "', " +
                "'" + kadListItem.getEndDate() + "" + "', " +
                "'" + kadListItem.getProgressFrag() + "', " +
                "'" + kadListItem.isSettingFrag() + "')");

        sqLiteDatabase.close();
    }

    private int getDatabaseCount(){
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();
        int recodeCount = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase,TABLE_NAME);
        Log.d("tesutesu","recodeCount:"+recodeCount);
        return recodeCount;
    }

    public void updateKadDateProgress(KadListItem kadListItem) {
        //読み込みモードで開く
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Log.d("KadListRecycle",kadListItem.getProgressFrag() + "");

        ContentValues cv = new ContentValues();
        cv.put(PROGRESS_COLUMN_NAME,kadListItem.getProgressFrag());

        sqLiteDatabase.update(TABLE_NAME,cv,KAD_ID_COLUMN_NAME + " = " + kadListItem.getKadId(),null);

        sqLiteDatabase.close();
    }


}
