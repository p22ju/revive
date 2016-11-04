package com.navercorp.jiwoo.revive.UI.DetailTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.navercorp.jiwoo.revive.R;

import java.util.ArrayList;

/**
 * Created by Jiwoo Ma on 2016-10-17.
 */
public class ExpenseCustomListAdapter extends BaseAdapter {

    private ArrayList<ExpenseListItem> mItems = new ArrayList<>();

    public ExpenseCustomListAdapter(ArrayList<ExpenseListItem> mItem) {
        this.mItems = mItem;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expenselistview_item, parent, false);
        }

        TextView dateTextView = (TextView) convertView.findViewById(R.id.expList_date);
        TextView cardTypeTextView = (TextView) convertView.findViewById(R.id.expList_cardType);
        TextView detailTextView = (TextView) convertView.findViewById(R.id.expList_detail);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.expList_price);

        ExpenseListItem expenseListItem = mItems.get(position);

        dateTextView.setText(expenseListItem.getExpenseDate());
        cardTypeTextView.setText(expenseListItem.getCardType());
        detailTextView.setText(expenseListItem.getExpenseDetail());
        priceTextView.setText(expenseListItem.getExpensePrice());

        return convertView;
    }

    //아이템 데이터 추가를 위한 함수
    public void addItem(String date, String cType, String detail, String price, String accum) {
        ExpenseListItem item = new ExpenseListItem();

        item.setExpenseDate(date);
        item.setCardType(cType);
        item.setExpenseDetail(detail);
        item.setExpensePrice(price);
        item.setAccumulatedExpense(accum);

        mItems.add(item);
    }

}
