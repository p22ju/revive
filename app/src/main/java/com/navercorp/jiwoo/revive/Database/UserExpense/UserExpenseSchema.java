package com.navercorp.jiwoo.revive.Database.UserExpense;

import java.util.Date;

/**
 * Created by Jiwoo Ma on 2016-11-25.
 * 지출내역 DB 스키마
 * (ID, 날짜, 카드타입, 상세설명, 지출금액)
 */

public class UserExpenseSchema {

    public static final class UserExpenseTable {
        public static final String NAME = "userexpense";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String DATE = "date";
            public static final String CARDTYPE = "cardtype";
            public static final String DESC = "description";
            public static final String EXPENDITURE = "expenditure";
        }
    }

}
