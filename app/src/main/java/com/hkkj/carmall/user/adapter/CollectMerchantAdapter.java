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

import static com.hkkj.carmall.R.id.user_iv_merchant_image;

/**
 * Created by 李大为 on 2019/7/22.
 */

public class CollectMerchantAdapter extends BaseQuickAdapter<CollectBean,BaseViewHolder> {

    public CollectMerchantAdapter(@LayoutRes int layoutResId, @Nullable List<CollectBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectBean item) {
        helper.setText(R.id.user_tv_merchant_name,item.getTitle());
        GlideImageUtils.display(mContext,item.getCover(),(ImageView) helper.getView(user_iv_merchant_image));
        helper.addOnClickListener(R.id.user_ll_merchant);
    }
}
