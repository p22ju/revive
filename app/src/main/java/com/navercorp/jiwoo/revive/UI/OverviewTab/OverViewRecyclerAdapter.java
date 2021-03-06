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
public class OverViewRecyclerAdapter extends RecyclerView.Adapter<OverViewRecyclerAdapter.MyViewHolder> {

    //TODO 이 어댑터에서 context가 필요할것인가,아닌가 더정확히 판단할것
    //TODO 그리고Q) 리스트어댑터에서 보통 context가 필요하게되는 경우가 있나, 있다면 보통 어떤 경우인가 (아님말고)
    //private Context mContext;
    private ArrayList<OverViewSingleItem> mItems;

    public OverViewRecyclerAdapter(ArrayList<OverViewSingleItem> data) {
        //mContext = context;
        mItems = data;
    }


    // 필수로 generate되어야 하는 메소드1 : 새로운 뷰 생성
    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_overview_single, parent, false);
        MyViewHolder myHolder = new MyViewHolder(v);
        return myHolder;
    }

    // 필수로 generate되어야 하는 메소드2 : ListView의 getView 부분을 담당하는 메소드
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OverViewSingleItem overViewSingleItem = mItems.get(position);
        holder.mTextViewForCardType.setText(overViewSingleItem.getCardType());
        holder.mTextViewForExpense.setText(overViewSingleItem.getCurrentSpending());
        holder.mTextViewForTargetBudgetCut.setText(overViewSingleItem.getTargetBudget());
    }

    // 필수로 generate되어야 하는 메소드3
    @Override
    public int getItemCount() {
        return mItems.size();
    }


    //TODO 이거 뭔지 정확히 알아내기
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewForCardType;
        public TextView mTextViewForExpense;
        public TextView mTextViewForTargetBudgetCut;

        public MyViewHolder(View view) {
            super(view);
            mTextViewForCardType = (TextView) view.findViewById(R.id.overviewList_card_type);
            mTextViewForExpense = (TextView) view.findViewById(R.id.overviewList_current_spending);
            mTextViewForTargetBudgetCut = (TextView) view.findViewById(R.id.overviewList_target_budget);
        }
    }

}
