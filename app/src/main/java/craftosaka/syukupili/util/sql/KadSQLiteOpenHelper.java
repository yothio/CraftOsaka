package craftosaka.syukupili.util.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yocchi on 2017/08/17.
 */

public class KadSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String KAD_ID_COLUMN_NAME = "kad_id";
    public static final String KAD_NAME_COLUMN_NAME = "kad_name";
    public static final String KAD_CONTENT_COLUMN_NAME = "kad_content";
    public static final String CHILD_ID_COLUMN_NAME = "child_id";
    public static final String CHILD_NAME_COLUMN_NAME = "child_name";
    public static final String POINT_COLUMN_NAME = "point";
    public static final String START_DATE_COLUMN_NAME = "start_date";
    public static final String END_DATE_COLUMN_NAME = "end_date";
    public static final String PROGRESS_COLUMN_NAME = "progress_frag";
    public static final String SETTING_COLUMN_NAME = "setting_frag";

    public static final String DB = "Kad.db";
    static final int DB_VERSION = 3;
    public static final String TABLE_NAME = "KadTable";
    static final String CREATE_TABLE =
            "create table " + TABLE_NAME +
                    " ( kad_id integer primary key, " +
                    "kad_name text not null , " +
                    "kad_content text not null , " +
                    "child_id text not null , " +
                    "child_name text not null , " +
                    "point text not null , " +
                    "start_date text not null , " +
                    "end_date text not null , " +
                    "progress_frag text not null , " +
                    "setting_frag text not null);";
    final static String TAG = "KadSQLiteOpenHelper";
    static final String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    public KadSQLiteOpenHelper(Context context) {
        super(context, DB, null, DB_VERSION);


        Log.d(TAG, "Call Const");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "create");
        createTable(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);

    }

    //
    private void createTable(SQLiteDatabase db) {
        db.beginTransaction();
        Log.d(TAG, "トランザクションの開始");
        try {
            db.execSQL(CREATE_TABLE);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            Log.d(TAG, db.toString());

            Log.d(TAG, "トランザクションの終了");
        }
    }
}
