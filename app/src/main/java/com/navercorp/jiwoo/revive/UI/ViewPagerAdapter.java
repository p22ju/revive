package com.navercorp.jiwoo.revive.UI;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.navercorp.jiwoo.revive.R;
import com.navercorp.jiwoo.revive.UI.DetailTab.ExpenseCustomListAdapter;
import com.navercorp.jiwoo.revive.UI.DetailTab.ExpenseListViewItem;
import com.navercorp.jiwoo.revive.UI.OverviewTab.RAdapter;
import com.navercorp.jiwoo.revive.UI.OverviewTab.SingleCardViewItem;
import com.navercorp.jiwoo.revive.UI.OverviewTab.TopCardViewItem;

import java.util.ArrayList;

/**
 * Created by Jiwoo Ma on 2016-10-17.
 */
public class ViewPagerAdapter extends PagerAdapter {

    LayoutInflater inflater;
    Context mContext;


    public ViewPagerAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;

        if(position == 0) {
            view = inflater.inflate(R.layout.viewpager_childview_expense, null);
            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            mRecyclerView.setLayoutManager(mLayoutManager);

            ArrayList<SingleCardViewItem> cardViewItems = cardsMockData();
            RecyclerView.Adapter mRAdapter = new RAdapter(cardViewItems);
            mRecyclerView.setAdapter(mRAdapter);

        } else if(position == 1) {
            view = inflater.inflate(R.layout.viewpager_childview_detail ,null);
            ListView mListView = (ListView) view.findViewById(R.id.listView);
            ArrayList<ExpenseListViewItem> expenseItems = mockData();
            ExpenseCustomListAdapter mExpenseCustomAdapter = new ExpenseCustomListAdapter(expenseItems);
            mListView.setAdapter(mExpenseCustomAdapter);
        }

        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private ArrayList<SingleCardViewItem> cardsMockData() {
        ArrayList<SingleCardViewItem> cardItems = new ArrayList<>();
        TopCardViewItem topCardViewItem = new TopCardViewItem();
        topCardViewItem.setTopFixedTitle("예산이 350만원 남았습니다.");
        topCardViewItem.setExpense("1340000");
        topCardViewItem.setTargetBudgetCut("450만");
        cardItems.add(topCardViewItem);

        SingleCardViewItem singleCardViewItem = new SingleCardViewItem();
        singleCardViewItem.setCardName("롯데");
        singleCardViewItem.setExpense("6400");
        singleCardViewItem.setTargetBudgetCut("150만");
        cardItems.add(singleCardViewItem);

        singleCardViewItem = new SingleCardViewItem();
        singleCardViewItem.setCardName("비씨");
        singleCardViewItem.setExpense("4400");
        singleCardViewItem.setTargetBudgetCut("200만");
        cardItems.add(singleCardViewItem);

        singleCardViewItem = new SingleCardViewItem();
        singleCardViewItem.setCardName("신한");
        singleCardViewItem.setExpense("7900");
        singleCardViewItem.setTargetBudgetCut("100만");
        cardItems.add(singleCardViewItem);

        return cardItems;
    }

    private ArrayList<ExpenseListViewItem> mockData() {
        ArrayList<ExpenseListViewItem> expenseItems = new ArrayList<>();
        ExpenseListViewItem expenseListViewItem = new ExpenseListViewItem();
        expenseListViewItem.setExpenseDate("10-06");
        expenseListViewItem.setCardType("롯데");
        expenseListViewItem.setExpenseDetail("파리크라상");
        expenseListViewItem.setExpensePrice("6,400원");
        expenseItems.add(expenseListViewItem);

        expenseListViewItem = new ExpenseListViewItem();
        expenseListViewItem.setExpenseDate("10-08");
        expenseListViewItem.setCardType("비씨");
        expenseListViewItem.setExpenseDetail("파리크라상2");
        expenseListViewItem.setExpensePrice("4,400원");
        expenseItems.add(expenseListViewItem);

        expenseListViewItem = new ExpenseListViewItem();
        expenseListViewItem.setExpenseDate("10-09");
        expenseListViewItem.setCardType("신한");
        expenseListViewItem.setExpenseDetail("파리크라상4");
        expenseListViewItem.setExpensePrice("7,900원");
        expenseItems.add(expenseListViewItem);

        return expenseItems;
    }

}
