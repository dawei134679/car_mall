package com.hkkj.carmall.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.user.bean.MerchantBean;

import java.util.List;

/**
 * Created by 李大为 on 2019/6/28.
 */

public class MerchantAdapter extends BaseQuickAdapter<MerchantBean,BaseViewHolder> {

    public MerchantAdapter(@LayoutRes int layoutResId, @Nullable List<MerchantBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MerchantBean item) {

    }
}
