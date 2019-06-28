package com.hkkj.carmall.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.user.bean.CommodityBean;

import java.util.List;

/**
 * Created by Administrator on 2019/6/28.
 */

public class CommodityAdapter extends BaseQuickAdapter<CommodityBean,BaseViewHolder> {
    public CommodityAdapter(@LayoutRes int layoutResId, @Nullable List<CommodityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityBean item) {
        helper.setText(R.id.user_tv_commodity_describe,item.getDescribe());
    }
}
