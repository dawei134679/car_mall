package com.hkkj.carmall.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 李大为 on 2019/7/3.
 */

public class MaxRecyclerView extends RecyclerView {

    public MaxRecyclerView(android.content.Context context, android.util.AttributeSet attrs){
        super(context, attrs);
    }
    public MaxRecyclerView(android.content.Context context){
        super(context);
    }
    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

