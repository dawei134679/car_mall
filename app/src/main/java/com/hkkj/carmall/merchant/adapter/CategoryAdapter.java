package com.hkkj.carmall.merchant.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.CategoryBean;

import java.util.List;


public class CategoryAdapter extends BaseQuickAdapter<CategoryBean,BaseViewHolder> {
    public CategoryAdapter(@LayoutRes int layoutResId, @Nullable List<CategoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryBean item) {
        helper.setText(R.id.category_item_name,item.getName());
        helper.addOnClickListener(R.id.category_item_name);
        helper.addOnClickListener(R.id.category_item_delete);
    }
}
