package com.hkkj.carmall.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.CommodityBean;
import com.hkkj.carmall.utils.GlideImageUtils;

import java.util.List;

import static com.hkkj.carmall.R.id.user_iv_commodity_imgurl;

/**
 * Created by 李大为 on 2019/6/28.
 */

public class CommodityAdapter extends BaseQuickAdapter<CommodityBean,BaseViewHolder> {
    public CommodityAdapter(@LayoutRes int layoutResId, @Nullable List<CommodityBean> data) {
         super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityBean item) {
        helper.setText(R.id.user_tv_commodity_describe,item.getCommodityDescription())
                .setText(R.id.user_tv_commodity_amount,"￥"+item.getAmount().toString());
        GlideImageUtils.display(mContext,item.getCommodityImageUrl(),(ImageView) helper.getView(user_iv_commodity_imgurl));
        helper.addOnClickListener(R.id.user_iv_commodity_imgurl);
        helper.addOnClickListener(R.id.user_tv_commodity_buy);

    }
}
