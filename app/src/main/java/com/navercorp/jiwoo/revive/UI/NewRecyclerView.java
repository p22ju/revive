package com.navercorp.jiwoo.revive.UI;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;

/**
 * Created by Jiwoo Ma on 2016-11-04.
 */
public class NewRecyclerView extends RecyclerView{

    public NewRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Adapter listAdapter = getAdapter();
        listAdapter.getItemCount();
        int newHeight = 0;
        final int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        DisplayManagerCompat displayManagerCompat = DisplayManagerCompat.getInstance(getContext());
        Display[] displayList = displayManagerCompat.getDisplays();

        newHeight = heightSize;
        for(Display disp : displayList) {
            newHeight = Math.max(disp.getHeight() , newHeight);
        }

        setMeasuredDimension(getMeasuredWidth(), newHeight);
    }
}
