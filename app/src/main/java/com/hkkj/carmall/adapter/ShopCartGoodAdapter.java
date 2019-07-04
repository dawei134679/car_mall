package com.hkkj.carmall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.ShopCartGoodBean;

import java.util.List;

/**
 * Created by Administrator on 2019/6/28.
 */

public class ShopCartGoodAdapter extends BaseQuickAdapter<ShopCartGoodBean,BaseViewHolder> {
    public ShopCartGoodAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartGoodBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartGoodBean item) {
       helper.setText(R.id.iv_stcg_good_describe,item.getDescribe());
    }
}
