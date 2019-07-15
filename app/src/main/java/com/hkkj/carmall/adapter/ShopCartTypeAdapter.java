package com.hkkj.carmall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.ShopCarNameBean;

import java.util.List;

/**
 * Created by 李大为 on 2019/6/28.
 */

public class ShopCartTypeAdapter extends BaseQuickAdapter<ShopCarNameBean,BaseViewHolder> {
    public ShopCartTypeAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCarNameBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCarNameBean item) {
        helper.setText(R.id.tv_shop_cartName,item.getName());
        RecyclerView rvRSCT = helper.getView(R.id.rv_shop_cart_type_cart);
        rvRSCT.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        ShopCartGoodAdapter shopCartGoodAdapter = new ShopCartGoodAdapter(R.layout.item_shop_type_cart_goods,item.getSccDatas());
        rvRSCT.setAdapter(shopCartGoodAdapter);
    }
}
