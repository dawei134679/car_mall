package com.hkkj.carmall.order.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hkkj.carmall.R;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.order.adapter.OrderAdapter;
import com.hkkj.carmall.order.bean.OrderBean;
import com.hkkj.carmall.order.bean.SubOrderBean;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends BaseFragment {

    private RecyclerView rvOrder;

    private OrderAdapter orderAdapter;

    private List<OrderBean> datas;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_order, null);
        datas = new ArrayList<OrderBean>();
        OrderBean orderBean1 = new OrderBean();
        List<SubOrderBean> subOrderBean1 = new ArrayList<SubOrderBean>();
        subOrderBean1.add(new SubOrderBean());
        subOrderBean1.add(new SubOrderBean());
        subOrderBean1.add(new SubOrderBean());
        orderBean1.setSubOrderList(subOrderBean1);

        OrderBean orderBean2 = new OrderBean();
        List<SubOrderBean> subOrderBean2 = new ArrayList<SubOrderBean>();
        subOrderBean2.add(new SubOrderBean());
        subOrderBean2.add(new SubOrderBean());
        orderBean2.setSubOrderList(subOrderBean2);

        OrderBean orderBean3 = new OrderBean();
        List<SubOrderBean> subOrderBean3 = new ArrayList<SubOrderBean>();
        subOrderBean3.add(new SubOrderBean());
        orderBean3.setSubOrderList(subOrderBean3);

        datas.add(orderBean1);
        datas.add(orderBean2);
        datas.add(orderBean3);

        rvOrder = view.findViewById(R.id.rvOrder);

        rvOrder.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        //创建订单适配器
        orderAdapter = new OrderAdapter(R.layout.item_order, datas);
        rvOrder.setAdapter(orderAdapter);
        return view;
    }

    @Override
    public void initData() {

    }
}
