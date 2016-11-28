package com.navercorp.jiwoo.revive.TempFiles;

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
import com.navercorp.jiwoo.revive.Database.UserExpense.DetailViewSingleItem;
import com.navercorp.jiwoo.revive.UI.OverviewTab.OverViewRecyclerAdapter;
import com.navercorp.jiwoo.revive.UI.OverviewTab.OverViewSingleItem;
import com.navercorp.jiwoo.revive.UI.OverviewTab.OverViewTopItem;

import java.util.ArrayList;

/**
 * Created by Jiwoo Ma on 2016-10-17.
 */
public class ViewPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context) {
        mContext = context;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;

        if(position == 0) {
            view = inflater.inflate(R.layout.viewpager_childview_expense, null);
            RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_for_expense_page);
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            mRecyclerView.setLayoutManager(mLayoutManager);

            ArrayList<OverViewSingleItem> cardViewItems = cardsMockData();
            RecyclerView.Adapter mRAdapter = new OverViewRecyclerAdapter(cardViewItems);
            mRecyclerView.setAdapter(mRAdapter);

        } else if(position == 1) {
            view = inflater.inflate(R.layout.viewpager_childview_detail ,null);
            ListView mListView = (ListView) view.findViewById(R.id.listView);
            ArrayList<DetailViewSingleItem> expenseItems = mockData();
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

    private ArrayList<OverViewSingleItem> cardsMockData() {
        ArrayList<OverViewSingleItem> cardItems = new ArrayList<>();
        OverViewTopItem overViewTopItem = new OverViewTopItem();
        overViewTopItem.setTopFixedTitle("예산이 350만원 남았습니다.");
        overViewTopItem.setCurrentSpending("1340000");
        overViewTopItem.setTargetBudget("450만");
        cardItems.add(overViewTopItem);

        OverViewSingleItem overViewSingleItem = new OverViewSingleItem();
        overViewSingleItem.setCardType("롯데");
        overViewSingleItem.setCurrentSpending("6400");
        overViewSingleItem.setTargetBudget("150만");
        cardItems.add(overViewSingleItem);

        overViewSingleItem = new OverViewSingleItem();
        overViewSingleItem.setCardType("비씨");
        overViewSingleItem.setCurrentSpending("4400");
        overViewSingleItem.setTargetBudget("200만");
        cardItems.add(overViewSingleItem);

        overViewSingleItem = new OverViewSingleItem();
        overViewSingleItem.setCardType("신한");
        overViewSingleItem.setCurrentSpending("7900");
        overViewSingleItem.setTargetBudget("100만");
        cardItems.add(overViewSingleItem);

        return cardItems;
    }

    private ArrayList<DetailViewSingleItem> mockData() {
        ArrayList<DetailViewSingleItem> expenseItems = new ArrayList<>();
        DetailViewSingleItem detailViewSingleItem = new DetailViewSingleItem();
        detailViewSingleItem.setExpenseDate("10-06");
        detailViewSingleItem.setCardType("롯데");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6400);
        expenseItems.add(detailViewSingleItem);

        detailViewSingleItem = new DetailViewSingleItem();
        detailViewSingleItem.setExpenseDate("10-08");
        detailViewSingleItem.setCardType("비씨");
        detailViewSingleItem.setExpenseDesc("파리크라상2");
        detailViewSingleItem.setExpenditure(4400);
        expenseItems.add(detailViewSingleItem);

        detailViewSingleItem = new DetailViewSingleItem();
        detailViewSingleItem.setExpenseDate("10-09");
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDesc("파리크라상4");
        detailViewSingleItem.setExpenditure(7900);
        expenseItems.add(detailViewSingleItem);

        return expenseItems;
    }

}
