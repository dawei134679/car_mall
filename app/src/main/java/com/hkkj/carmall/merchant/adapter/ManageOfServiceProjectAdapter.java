package com.hkkj.carmall.merchant.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.Switch;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.ServiceProjectBean;

import java.util.List;


public class ManageOfServiceProjectAdapter extends BaseQuickAdapter<ServiceProjectBean,BaseViewHolder> {
    public ManageOfServiceProjectAdapter(@LayoutRes int layoutResId, @Nullable List<ServiceProjectBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServiceProjectBean item) {
        helper.setText(R.id.imosp_projectName,item.getServiceItemName());
        if (item.getStatus() == 1){
            Switch s = (Switch)helper.getView(R.id.imosp_projectSwitch);
            s.setChecked(true);
        }
        helper.addOnClickListener(R.id.imosp_projectSwitch);
    }
}
