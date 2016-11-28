package com.navercorp.jiwoo.revive.Database.UserExpense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jiwoo Ma on 2016-11-25.
 */

public class UserExpenseDBManager {
    private static UserExpenseDBManager sUserExpenseDBManager;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static UserExpenseDBManager get(Context context) {
        if(sUserExpenseDBManager == null) {
            sUserExpenseDBManager = new UserExpenseDBManager(context);
        }
        return sUserExpenseDBManager;
    }

    private UserExpenseDBManager(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new UserExpenseBaseHelper(mContext).getWritableDatabase();
    }


    /*
    UserExpense 테이블에 값을 추가합니다.
     */
    public void addUserExpenseDetail(DetailViewSingleItem d) {
        ContentValues values = getContentValues(d);
        mDatabase.insert(UserExpenseSchema.UserExpenseTable.NAME, null, values);
    }

    /*
    UserExpense 테이블에 있는
    모든 값을 가져옵니다.
     */
    public List<DetailViewSingleItem> getAllUserExpenseDetail(UUID id) {
        List<DetailViewSingleItem> detailViewSingleItems = new ArrayList<>();
        UserExpenseCursorWrapper cursor = queryUserExpense(null,null);

        try {
            cursor.moveToFirst();
            while (! cursor.isAfterLast()) {
                detailViewSingleItems.add(cursor.getUserExpenseDetail());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return detailViewSingleItems;
    }

    /*
    UserExpense 테이블에서,
    해당id가 가진 값을 가져옵니다.
     */
    public DetailViewSingleItem getUserExpenseDetail(UUID id) {
        UserExpenseCursorWrapper cursor = queryUserExpense(
                UserExpenseSchema.UserExpenseTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if(cursor.getCount() == 0) {
                return  null;
            }
            cursor.moveToFirst();
            return  cursor.getUserExpenseDetail();
        } finally {
            cursor.close();
        }
    }

    /*
    UserExpense 테이블을 업데이트 합니다.
     */
    public void updateUserExpense(DetailViewSingleItem d) {
        String uuidString = d.getId().toString();
        ContentValues values = getContentValues(d);

        mDatabase.update(UserExpenseSchema.UserExpenseTable.NAME, values,
                UserExpenseSchema.UserExpenseTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }


    /*
    UserExpense 테이블의 데이터를 읽습니다. (쿼리합니다.)
     */
    private UserExpenseCursorWrapper queryUserExpense(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                UserExpenseSchema.UserExpenseTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new UserExpenseCursorWrapper(cursor);
    }


    /*
    DetailViewSingleItem 모델을
    SQLite에 적합한 ContentValue 값으로
    변환해줍니다.
     */
    private ContentValues getContentValues(DetailViewSingleItem detailViewSingleItem) {
        ContentValues values = new ContentValues();
        values.put(UserExpenseSchema.UserExpenseTable.Cols.UUID, detailViewSingleItem.getId().toString());
        values.put(UserExpenseSchema.UserExpenseTable.Cols.CARDTYPE, detailViewSingleItem.getCardType().toString());
        values.put(UserExpenseSchema.UserExpenseTable.Cols.DATE, detailViewSingleItem.getExpenseDate().toString());
        values.put(UserExpenseSchema.UserExpenseTable.Cols.DESC, detailViewSingleItem.getExpenseDesc().toString());
        values.put(UserExpenseSchema.UserExpenseTable.Cols.EXPENDITURE, detailViewSingleItem.getExpenditure());

        return values;
    }
}
