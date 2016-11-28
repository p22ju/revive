package com.navercorp.jiwoo.revive.Database.UserInfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jiwoo Ma on 2016-11-21.
 */

public class UserInfoBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userInfo.db";


    public UserInfoBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserInfoDbSchema.UserInfoTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                UserInfoDbSchema.UserInfoTable.Cols.UUID + ", " +
                UserInfoDbSchema.UserInfoTable.Cols.USERNAME + ", " +
                UserInfoDbSchema.UserInfoTable.Cols.USEREMAIL + ", " +
                UserInfoDbSchema.UserInfoTable.Cols.USERBUDGETCUT +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserInfoDbSchema.UserInfoTable.NAME);
        onCreate(db);
    }

}
