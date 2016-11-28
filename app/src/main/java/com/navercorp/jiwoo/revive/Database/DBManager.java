package com.navercorp.jiwoo.revive.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jiwoo Ma on 2016-10-14.
 */
public class DBManager extends SQLiteOpenHelper {


    //생성자
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 최초 DB를 만들때 한번만 호출된다.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);
        db.execSQL(ExpenseDB.CreateDB._CREATE);
    }

    // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ExpenseDB.CreateDB._CREATE);
        onCreate(db);
    }




    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE " + ExpenseDB.CreateDB._TABLE_NAME);
        onCreate(db);
        db.close();
    }

    public String printData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("SELECT * from " + ExpenseDB.CreateDB._TABLE_NAME, null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + ": "
                    + cursor.getString(2)
                    + ", price = "
                    + cursor.getString(3)
                    + ", totalExp = "
                    + cursor.getString(4)
                    + "\n";
        }

        return str;
    }
}
