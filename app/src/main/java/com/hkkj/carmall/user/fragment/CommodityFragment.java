package com.hkkj.carmall.user.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hkkj.carmall.R;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.user.adapter.CommodityAdapter;
import com.hkkj.carmall.user.bean.CommodityBean;

import java.util.ArrayList;
import java.util.List;


public class CommodityFragment extends BaseFragment {

    private RecyclerView rvCommodity;

    private CommodityAdapter commodityAdapter;

    private List<CommodityBean> datas;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_commodity, null);
        rvCommodity = view.findViewById(R.id.rv_commodity);
        rvCommodity.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        initData();
        commodityAdapter = new CommodityAdapter(R.layout.item_commodity, datas);
        rvCommodity.setAdapter(commodityAdapter);
        return view;
    }

    @Override
    public void initData() {
        datas = new ArrayList<CommodityBean>();
        CommodityBean commodityBean1 = new CommodityBean();
        commodityBean1.setDescribe("商品1");
        commodityBean1.setPrice(555.0);
        CommodityBean commodityBean2 = new CommodityBean();
        commodityBean2.setDescribe("商品2");
        commodityBean2.setPrice(100.0);
        CommodityBean commodityBean3 = new CommodityBean();
        commodityBean3.setDescribe("商品3");
        commodityBean3.setPrice(55.0);
        datas.add(commodityBean1);
        datas.add(commodityBean2);
        datas.add(commodityBean3);
    }
}
