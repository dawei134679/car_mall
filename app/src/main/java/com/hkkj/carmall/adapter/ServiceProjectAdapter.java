package com.hkkj.carmall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.ServiceProjectBean;

import java.util.List;

/**
 * Created by 李大为 on 2019/6/28.
 */

public class ServiceProjectAdapter extends BaseQuickAdapter<ServiceProjectBean,BaseViewHolder> {
    public ServiceProjectAdapter(@LayoutRes int layoutResId, @Nullable List<ServiceProjectBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServiceProjectBean item) {
        helper.setText(R.id.tv_project_name,item.getName());
    }
}
