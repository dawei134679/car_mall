package com.hkkj.carmall.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.user.bean.WcardsBean;

import java.util.List;

/**
 * Created by 李大为 on 2019/6/26.
 */

public class WcardsAdapter extends BaseQuickAdapter<WcardsBean,BaseViewHolder> {

    public WcardsAdapter(@LayoutRes int layoutResId, @Nullable List<WcardsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WcardsBean item) {
        helper.setText(R.id.tv_wcard_name, item.getCardName());
    }
}
