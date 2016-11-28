package com.navercorp.jiwoo.revive.UI.DetailTab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.navercorp.jiwoo.revive.Database.UserExpense.DetailViewSingleItem;
import com.navercorp.jiwoo.revive.R;

import java.util.ArrayList;

/**
 * Created by Jiwoo Ma on 2016-10-19.
 */
public class DetailViewRecyclerAdapter extends RecyclerView.Adapter<DetailViewRecyclerAdapter.MyViewHolder> {

    //TODO 이 어댑터에서 context가 필요할것인가,아닌가 더정확히 판단할것
    //TODO 그리고Q) 리스트어댑터에서 보통 context가 필요하게되는 경우가 있나, 있다면 보통 어떤 경우인가 (아님말고)
    //private Context mContext;
    private ArrayList<DetailViewSingleItem> mItems;

    public DetailViewRecyclerAdapter(ArrayList<DetailViewSingleItem> data) {
        //mContext = context;
        mItems = data;
    }


    // 필수로 generate되어야 하는 메소드1 : 새로운 뷰 생성
    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_detailview_single, parent, false);
        MyViewHolder myHolder = new MyViewHolder(v);
        return myHolder;
    }

    // 필수로 generate되어야 하는 메소드2 : ListView의 getView 부분을 담당하는 메소드
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DetailViewSingleItem detailViewSingleItem= mItems.get(position);
        holder.mTextViewForSpendingDate.setText(detailViewSingleItem.getExpenseDate());
        holder.mTextViewForCardType.setText(detailViewSingleItem.getCardType());
        holder.mTextViewForSpendingDescription.setText(detailViewSingleItem.getExpenseDesc());
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
        public TextView mTextViewForSpendingDate;
        public TextView mTextViewForCardType;
        public TextView mTextViewForSpendingDescription;
        public TextView mTextViewForSpending;

        public MyViewHolder(View view) {
            super(view);
            mTextViewForSpendingDate = (TextView) view.findViewById(R.id.detailview_date);
            mTextViewForCardType = (TextView) view.findViewById(R.id.detailview_card_type);
            mTextViewForSpendingDescription = (TextView) view.findViewById(R.id.detailview_desc);
            mTextViewForSpending = (TextView) view.findViewById(R.id.detailview_expense);
        }
    }

}
