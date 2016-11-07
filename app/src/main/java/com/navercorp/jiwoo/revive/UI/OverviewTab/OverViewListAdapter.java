package com.navercorp.jiwoo.revive.UI.OverviewTab;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.navercorp.jiwoo.revive.R;

import java.util.ArrayList;

/**
 * Created by Jiwoo Ma on 2016-11-04.
 */
public class OverViewListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<OverViewItem> mItems;

    public OverViewListAdapter(Context context, ArrayList<OverViewItem> items) {
        mContext = context;
        mItems = items;
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
        OverViewItem overViewItem = mItems.get(position);

        if(convertView == null) {
            // TODO null, parent 차이
            convertView = View.inflate(parent.getContext(), R.layout.singlecontainerview_layout, null);
        }

        TextView textViewForCardType = (TextView) convertView.findViewById(R.id.overviewList_cardType);
        TextView textViewForBudget = (TextView) convertView.findViewById(R.id.overviewList_targetBudget);
        TextView textViewForTotal = (TextView) convertView.findViewById(R.id.overviewList_expense);

        textViewForCardType.setText(overViewItem.getCardType());
        textViewForBudget.setText(overViewItem.getTargetBudget());
        textViewForTotal.setText(overViewItem.getTotalExpense());

        return convertView;
    }
}
