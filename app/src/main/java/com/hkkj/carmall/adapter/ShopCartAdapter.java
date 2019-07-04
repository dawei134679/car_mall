package com.hkkj.carmall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.ShopCartBean;

import java.util.List;

/**
 * Created by Administrator on 2019/6/28.
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartBean,BaseViewHolder> {
    public ShopCartAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartBean item) {
        String statusText = null;
        if(item.getType() == 0){
            statusText = "到店服务";
        }
        if(item.getType() == 1){
            statusText = "移动服务";
        }
        helper.setText(R.id.tv_shop_cart_type,statusText);
        RecyclerView rvRST = helper.getView(R.id.rv_shop_cart_type);
        rvRST.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        ShopCartTypeAdapter shopCartTypeAdapter = new ShopCartTypeAdapter(R.layout.item_shop_type_cart,item.getScnDatas());
        rvRST.setAdapter(shopCartTypeAdapter);
    }
}
