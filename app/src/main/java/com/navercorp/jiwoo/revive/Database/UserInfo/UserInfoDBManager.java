package com.navercorp.jiwoo.revive.Database.UserInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jiwoo Ma on 2016-11-22.
 */

public class UserInfoDBManager {
    private static UserInfoDBManager sUserInfoDBManager;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    /*
    UserInfoDBManager는
    싱글턴 객체입니다.
     */
    public static UserInfoDBManager get(Context context) {
        if(sUserInfoDBManager == null) {
            sUserInfoDBManager = new UserInfoDBManager(context);
        }
        return  sUserInfoDBManager;
    }

    private UserInfoDBManager(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new UserInfoBaseHelper(mContext).getWritableDatabase();
    }



    /*
    UserInfo 테이블에 값을 추가합니다.
     */
    public void addUserInfo(UserInfoItem u) {
        ContentValues values = getContentValues(u);
        mDatabase.insert(UserInfoDbSchema.UserInfoTable.NAME, null, values);
    }


    /*
    UserInfo 테이블에 있는
    모든 값을 가져옵니다.
     */
    public List<UserInfoItem> getAllUserInfo() {
        List<UserInfoItem> userInfos = new ArrayList<>();
        UserInfoCursorWrapper cursor = queryUserInfo(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                userInfos.add(cursor.getUserInfo());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return userInfos;
    }

    /*
    UserInfo 테이블에서,
    해당id가 가진 값을 가져옵니다.
     */
    public UserInfoItem getUserInfo(UUID id) {
        UserInfoCursorWrapper cursor = queryUserInfo(
                UserInfoDbSchema.UserInfoTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if(cursor.getCount() == 0) {
                return  null;
            }
            cursor.moveToFirst();
            return cursor.getUserInfo();
        } finally {
            cursor.close();
        }
    }

    /*
    UserInfo 테이블을 없데이트 합니다.
     */
    public void updateUserInfo(UserInfoItem u) {
        String uuidString = u.getId().toString();
        ContentValues values = getContentValues(u);

        mDatabase.update(UserInfoDbSchema.UserInfoTable.NAME, values,
                UserInfoDbSchema.UserInfoTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }


    /*
    UserInfoItem 테이블의 데이터를 읽습니다. (쿼리합니다.)
     */
    private UserInfoCursorWrapper queryUserInfo(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                UserInfoDbSchema.UserInfoTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new UserInfoCursorWrapper(cursor);
    }

    /*
    UserInfoItem 모델을
    SQLite에 적합한 ContentValue 값으로
    변환해줍니다.
     */
    private static ContentValues getContentValues(UserInfoItem userInfoItem) {
        ContentValues values = new ContentValues();
        values.put(UserInfoDbSchema.UserInfoTable.Cols.UUID, userInfoItem.getId().toString());
        values.put(UserInfoDbSchema.UserInfoTable.Cols.USERNAME, userInfoItem.getUserName().toString());
        values.put(UserInfoDbSchema.UserInfoTable.Cols.USEREMAIL, userInfoItem.getUserEmail().toString());
        values.put(UserInfoDbSchema.UserInfoTable.Cols.USERBUDGETCUT, userInfoItem.getUserBudgeCut());

        return values;
    }
}
