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
    private ArrayList<OverViewSingleItem> mItems;

    public OverViewListAdapter(Context context, ArrayList<OverViewSingleItem> items) {
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
        OverViewSingleItem overViewItem = mItems.get(position);

        if(convertView == null) {
            // TODO null, parent 차이
            convertView = View.inflate(parent.getContext(), R.layout.layout_overview_single, null);
        }

        TextView textViewForCardType = (TextView) convertView.findViewById(R.id.overviewList_card_type);
        TextView textViewForBudget = (TextView) convertView.findViewById(R.id.overviewList_target_budget);
        TextView textViewForCurrentSpending = (TextView) convertView.findViewById(R.id.overviewList_current_spending);

        textViewForCardType.setText(overViewItem.getCardType());
        textViewForBudget.setText(overViewItem.getTargetBudget());
        textViewForCurrentSpending.setText(overViewItem.getCurrentSpending());

        return convertView;
    }
}
