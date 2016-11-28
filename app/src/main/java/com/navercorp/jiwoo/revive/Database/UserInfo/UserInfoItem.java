package com.navercorp.jiwoo.revive.Database.UserInfo;

import java.util.UUID;

/**
 * Created by Jiwoo Ma on 2016-11-21.
 */

public class UserInfoItem {

    private UUID mId;
    private String mUserName;
    private String mUserEmail;
    private int mUserBudgeCut;

    public UserInfoItem() {
        this(UUID.randomUUID());
    }

    public UserInfoItem(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }

    public int getUserBudgeCut() {
        return mUserBudgeCut;
    }

    public void setUserBudgeCut(int userBudgeCut) {
        mUserBudgeCut = userBudgeCut;
    }
}
