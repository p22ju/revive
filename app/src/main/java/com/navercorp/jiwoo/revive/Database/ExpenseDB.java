package com.navercorp.jiwoo.revive.Database;

import android.provider.BaseColumns;

/**
 * Created by Jiwoo Ma on 2016-10-14.
 * 지출내역 DB 스키마
 */
public class ExpenseDB {

    public static final class CreateDB implements BaseColumns {

        public static final String _TABLE_NAME = "EXPENSE_LIST";
        public static final String _NUM = "num";
        public static final String _DATE = "date";
        public static final String _CARD_TYPE = "card_type";
        public static final String _DETAIL = "detail";
        public static final String _PRICE = "price";
        public static final String _ACCUMULATED_EXPENSE = "accumulated_expense";

        public static final String _CREATE =
                "CREATE TABLE " + _TABLE_NAME + "("
                        + _NUM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + _DATE + " TEXT, "
                        + _CARD_TYPE + " TEXT, "
                        + _DETAIL + " TEXT, "
                        + _PRICE + " INTEGER, "
                        + _ACCUMULATED_EXPENSE + " TEXT);";

    }

}
