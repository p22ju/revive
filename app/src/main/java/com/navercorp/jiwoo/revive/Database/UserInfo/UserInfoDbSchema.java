package com.navercorp.jiwoo.revive.Database.UserInfo;

/**
 * Created by Jiwoo Ma on 2016-11-21.
 * 사용자 정보 DB 스키마
 * (ID, 이름, 이메일, 지정목표한도)
 */

public class UserInfoDbSchema {

    public static final class UserInfoTable {
        public static final String NAME = "userinfo";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String USERNAME = "username";
            public static final String USEREMAIL = "useremail";
            public static final String USERBUDGETCUT = "userbudgetcut";
        }
    }


}
