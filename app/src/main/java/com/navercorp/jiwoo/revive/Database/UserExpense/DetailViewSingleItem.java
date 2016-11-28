package com.navercorp.jiwoo.revive.Database.UserExpense;

import java.util.UUID;

/**
 * Created by Jiwoo Ma on 2016-10-17.
 */
public class DetailViewSingleItem {


    private UUID mId;
    private String mExpenseDate;
    private String mCardType;
    private String mExpenseDesc;
    private int mExpenditure;

    public DetailViewSingleItem() {
        this(UUID.randomUUID());
    }

    public DetailViewSingleItem(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getExpenseDate() {
        return mExpenseDate;
    }

    public String getCardType() {
        return mCardType;
    }

    public void setCardType(String cardType) {
        mCardType = cardType;
    }

    public String getExpenseDesc() {
        return mExpenseDesc;
    }

    public void setExpenseDate(String expenseDate) {
        mExpenseDate = expenseDate;
    }

    public void setExpenseDesc(String expenseDesc) {
        mExpenseDesc = expenseDesc;
    }

    public int getExpenditure() {
        return mExpenditure;
    }

    public void setExpenditure(int expenditure) {
        mExpenditure = expenditure;
    }
}
