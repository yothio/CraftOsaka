package craftosaka.syukupili.util.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yocchi on 2017/08/17.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {


    public static final String DB = "train2.db";
    static final int DB_VERSION = 2;
    public static final String TABLE_NAME="KadTable";
    static final String CREATE_TABLE = "create table "+ TABLE_NAME +
                                    " ( kad_id integer primary key, " +
                                    "kad_name text not null , " +
                                    "kad_content text not null , " +
                                    "child_id text not null , " +
                                    "child_name text not null , " +
                                    "point text not null , " +
                                    "start_date text not null , " +
                                    "progress_frag text not null , " +
                                    "setting_frag text not null);";
    final static String TAG = "MySQLiteOpenHelper";
    static final String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    public MySQLiteOpenHelper(Context context){
        super(context,DB,null,DB_VERSION);

        Log.d(TAG,"Call Const");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);

    }

    //
    private void createTable(SQLiteDatabase db){
        db.beginTransaction();
        Log.d(TAG,"トランザクションの開始");
        try{
            db.execSQL(CREATE_TABLE);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            Log.d(TAG,db.toString());

            Log.d(TAG,"トランザクションの終了");
        }
    }
}
