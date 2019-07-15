package com.hkkj.carmall.order.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.order.bean.SubOrderBean;

import java.util.List;

/**
 * Created by 李大为 on 2019/6/26.
 */

public class SubOrderAdapter extends BaseQuickAdapter<SubOrderBean,BaseViewHolder> {
    public SubOrderAdapter(@LayoutRes int layoutResId, @Nullable List<SubOrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubOrderBean item) {
        helper.setText(R.id.good_describe, item.getDescribe())
                .setText(R.id.good_num,"×"+item.getNum())
                .setText(R.id.good_price,"￥"+item.getPrice())
                .setText(R.id.good_specification,item.getSpecification());
    }
}
