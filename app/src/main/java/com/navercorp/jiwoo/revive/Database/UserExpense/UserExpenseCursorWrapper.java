package com.navercorp.jiwoo.revive.Database.UserExpense;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

/**
 * Created by Jiwoo Ma on 2016-11-28.
 */

public class UserExpenseCursorWrapper extends CursorWrapper {

    public UserExpenseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public DetailViewSingleItem getUserExpenseDetail() {
        String uuidString = getString(getColumnIndex(UserExpenseSchema.UserExpenseTable.Cols.UUID));
        String cardType = getString(getColumnIndex(UserExpenseSchema.UserExpenseTable.Cols.CARDTYPE));
        String expenseDate = getString(getColumnIndex(UserExpenseSchema.UserExpenseTable.Cols.DATE));
        String expenseDesc = getString(getColumnIndex(UserExpenseSchema.UserExpenseTable.Cols.DESC));
        int expenditure = getInt(getColumnIndex(UserExpenseSchema.UserExpenseTable.Cols.EXPENDITURE));

        DetailViewSingleItem detailViewSingleItem = new DetailViewSingleItem(UUID.fromString(uuidString));
        detailViewSingleItem.setCardType(cardType);
        detailViewSingleItem.setExpenseDate(expenseDate);
        detailViewSingleItem.setExpenseDesc(expenseDesc);
        detailViewSingleItem.setExpenditure(expenditure);
        return detailViewSingleItem;
    }

}
