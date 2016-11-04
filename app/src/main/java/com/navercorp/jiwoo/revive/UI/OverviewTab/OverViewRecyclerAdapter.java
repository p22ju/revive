package com.navercorp.jiwoo.revive.UI.OverviewTab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.navercorp.jiwoo.revive.R;

import java.util.ArrayList;

/**
 * Created by Jiwoo Ma on 2016-11-02.
 */
public class OverViewRecyclerAdapter extends RecyclerView.Adapter<OverViewRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<OverViewItem> mItems;

    public OverViewRecyclerAdapter(Context context, ArrayList<OverViewItem> items) {
        mContext = context;
        mItems = items;
    }

    // 필수로 generate되어야 하는 메소드1 : 새로운 뷰 생성
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlecardview_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    // 필수로 gernerate되어야 하는 메소드2 : ListView의 getView부분을 담당하는 메소드
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OverViewItem overViewItem = mItems.get(position);
        holder.textViewForCardType.setText(overViewItem.getCardType());
        holder.textViewForTargetBudget.setText(overViewItem.getTargetBudget());
        holder.textViewForTotalExpense.setText(overViewItem.getTotalExpense());
    }

    // 필수로 generate되어야 하는 메소드3
    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewForCardType;
        public TextView textViewForTargetBudget;
        public TextView textViewForTotalExpense;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewForCardType = (TextView) itemView.findViewById(R.id.overviewList_cardType);
            textViewForTargetBudget = (TextView) itemView.findViewById(R.id.overviewList_targetBudget);
            textViewForTotalExpense = (TextView) itemView.findViewById(R.id.overviewList_expense);
        }
    }

}
