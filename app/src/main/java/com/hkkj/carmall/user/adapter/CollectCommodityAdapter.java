package com.hkkj.carmall.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.user.bean.CollectBean;
import com.hkkj.carmall.utils.GlideImageUtils;

import java.util.List;

import static com.hkkj.carmall.R.id.user_iv_commodity_imgurl;

/**
 * Created by 李大为 on 2019/6/28.
 */

public class CollectCommodityAdapter extends BaseQuickAdapter<CollectBean,BaseViewHolder> {

    public CollectCommodityAdapter(@LayoutRes int layoutResId, @Nullable List<CollectBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectBean item) {
        helper.setText(R.id.user_tv_commodity_describe,item.getTitle())
                .setText(R.id.user_tv_commodity_amount,item.getPrice()+"");
        GlideImageUtils.display(mContext,item.getCover(),(ImageView) helper.getView(user_iv_commodity_imgurl));
        helper.addOnClickListener(R.id.user_iv_commodity_imgurl);
        helper.addOnClickListener(R.id.user_tv_commodity_buy);
    }
}
