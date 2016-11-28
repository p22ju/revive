package com.navercorp.jiwoo.revive.UI.OverviewTab;

/**
 * Created by Jiwoo Ma on 2016-10-20.
 */
public class OverViewSingleItem {

    private String cardType;
    private String currentSpending;
    private String targetBudget;

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCurrentSpending(String currentSpending) {
        this.currentSpending = currentSpending;
    }

    public void setTargetBudget(String targetBudget) {
        this.targetBudget = targetBudget;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCurrentSpending() {
        return currentSpending;
    }

    public String getTargetBudget() {
        return targetBudget;
    }
}
