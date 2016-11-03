package com.navercorp.jiwoo.revive.UI.OverviewTab;

/**
 * Created by Jiwoo Ma on 2016-10-20.
 */
public class SingleCardViewItem {

    private String cardName;
    private String expense;
    private String targetBudgetCut;

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public void setTargetBudgetCut(String targetBudgetCut) {
        this.targetBudgetCut = targetBudgetCut;
    }

    public String getCardName() {
        return cardName;
    }

    public String getExpense() {
        return expense;
    }

    public String getTargetBudgetCut() {
        return targetBudgetCut;
    }
}
