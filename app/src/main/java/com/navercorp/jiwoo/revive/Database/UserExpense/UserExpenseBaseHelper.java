package com.navercorp.jiwoo.revive.Database.UserExpense;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Jiwoo Ma on 2016-11-25.
 */

public class UserExpenseBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userExpenseList.db";

    public UserExpenseBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + UserExpenseSchema.UserExpenseTable.NAME + "(" +
                UserExpenseSchema.UserExpenseTable.Cols.UUID + "," +
                UserExpenseSchema.UserExpenseTable.Cols.DATE + "," +
                UserExpenseSchema.UserExpenseTable.Cols.CARDTYPE+ "," +
                UserExpenseSchema.UserExpenseTable.Cols.DESC + "," +
                UserExpenseSchema.UserExpenseTable.Cols.EXPENDITURE +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserExpenseSchema.UserExpenseTable.NAME);
        onCreate(db);
    }
}
