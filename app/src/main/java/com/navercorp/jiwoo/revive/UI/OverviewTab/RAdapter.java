package com.navercorp.jiwoo.revive.UI.OverviewTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.navercorp.jiwoo.revive.R;

import java.util.ArrayList;

/**
 * Created by Jiwoo Ma on 2016-10-19.
 */
public class RAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<SingleCardViewItem> mItems = new ArrayList<>();

    public RAdapter(ArrayList<SingleCardViewItem> data) {
        mItems = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewForCardType;
        public TextView mTextViewForExpense;
        public TextView mTextViewForTargetBudgetCut;

        public ViewHolder(View view) {
            super(view);
            mTextViewForCardType = (TextView) view.findViewById(R.id.overviewList_cardType);
            mTextViewForExpense = (TextView) view.findViewById(R.id.overviewList_expense);
            mTextViewForTargetBudgetCut = (TextView) view.findViewById(R.id.overviewList_targetBudget);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecardview_layout, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
