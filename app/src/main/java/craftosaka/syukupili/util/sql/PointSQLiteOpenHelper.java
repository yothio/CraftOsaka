package craftosaka.syukupili.util.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yocchi on 2017/08/23.
 */

public class PointSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String ITEM_ID_COLUMN_NAME = "item_id";
    public static final String ITEM_NAME_COLUMN_NAME = "item_name";
    public static final String POINT_VALUE_COLUMN_NAME = "point";

    public static final String DB = "Point.db";
    static final int DB_VERSION = 3;
    public static final String TABLE_NAME = "PointTable";
    static final String CREATE_TABLE =
            "create table " + TABLE_NAME +
                    " ( " + ITEM_ID_COLUMN_NAME + " integer primary key, " +
                    "" + ITEM_NAME_COLUMN_NAME + " text not null , " +
                    ""+POINT_VALUE_COLUMN_NAME+"integer not null);";

    final static String TAG = "KadSQLiteOpenHelper";
    static final String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    public PointSQLiteOpenHelper(Context context) {
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
