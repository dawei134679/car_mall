package com.hkkj.carmall.order.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.order.bean.OrderBean;

import java.util.List;

/**
 * Created by 李大为 on 2019/6/26.
 */

public class OrderAdapter extends BaseQuickAdapter<OrderBean,BaseViewHolder> {

    public OrderAdapter(int layoutResId, List<OrderBean> datas) {
        super(layoutResId ,datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        String statusText = null;
        if(item.getStatus() == 0){
            statusText = "待付款";
        }
        if(item.getStatus() == 1){
            statusText = "待服务";
        }
        if(item.getStatus() == 2){
            statusText = "待完成";
        }
        if(item.getStatus() == 3){
            statusText = "待评价";
        }
        helper.setText(R.id.tv_order_status,statusText);
        RecyclerView rvGoods = helper.getView(R.id.rvGoods);
        rvGoods.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rvGoods.setHasFixedSize(true); //禁止recyclerview滑动，避免和ScrollView冲突；
        rvGoods.setNestedScrollingEnabled(false); //禁止recyclerview滑动，避免和ScrollView冲突；
        //赋值子订单
        SubOrderAdapter subOrderAdapter = new SubOrderAdapter(R.layout.item_order_goods, item.getSubOrderList());
        rvGoods.setAdapter(subOrderAdapter);
    }

}
