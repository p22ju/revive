package com.navercorp.jiwoo.revive.Database.UserInfo;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

/**
 * Created by Jiwoo Ma on 2016-11-25.
 */

public class UserInfoCursorWrapper extends CursorWrapper {


    public UserInfoCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public UserInfoItem getUserInfo() {
        String uuidString = getString(getColumnIndex(UserInfoDbSchema.UserInfoTable.Cols.UUID));
        String userName = getString(getColumnIndex(UserInfoDbSchema.UserInfoTable.Cols.USERNAME));
        String userEmail = getString(getColumnIndex(UserInfoDbSchema.UserInfoTable.Cols.USEREMAIL));
        int userBudgetCut = getInt(getColumnIndex(UserInfoDbSchema.UserInfoTable.Cols.USERBUDGETCUT));

        UserInfoItem userInfo = new UserInfoItem(UUID.fromString(uuidString));
        userInfo.setUserName(userName);
        userInfo.setUserEmail(userEmail);
        userInfo.setUserBudgeCut(userBudgetCut);
        return userInfo;
    }

}
