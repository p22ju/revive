package com.navercorp.jiwoo.revive.UI.DetailTab;

import android.widget.TextView;

/**
 * Created by Jiwoo Ma on 2016-10-17.
 */
public class ExpenseListItem {

    private String expenseDate;
    private String cardType;
    private String expenseDetail;
    private String expensePrice;
    private String accumulatedExpense;


    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setExpenseDetail(String expenseDetail) {
        this.expenseDetail = expenseDetail;
    }

    public void setExpensePrice(String expensePrice) {
        this.expensePrice = expensePrice;
    }

    public void setAccumulatedExpense(String accumulatedExpense) {
        this.accumulatedExpense = accumulatedExpense;
    }


    public String getExpenseDate() {
        return expenseDate;
    }

    public String getCardType() {
        return cardType;
    }

    public String getExpenseDetail() {
        return expenseDetail;
    }

    public String getExpensePrice() {
        return expensePrice;
    }

    public String getAccumulatedExpense() {
        return accumulatedExpense;
    }
}
