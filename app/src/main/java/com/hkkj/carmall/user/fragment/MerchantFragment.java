package com.hkkj.carmall.user.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hkkj.carmall.R;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.user.adapter.MerchantAdapter;
import com.hkkj.carmall.user.bean.MerchantBean;

import java.util.ArrayList;
import java.util.List;


public class MerchantFragment extends BaseFragment {

    private RecyclerView rvMerchant;

    private List<MerchantBean> datas;

    private MerchantAdapter merchantAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_merchant, null);
        initData();
        rvMerchant = view.findViewById(R.id.rv_merchant);
        rvMerchant.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
        merchantAdapter = new MerchantAdapter(R.layout.item_merchant,datas);
        rvMerchant.setAdapter(merchantAdapter);
        return view;
    }

    @Override
    public void initData() {
        datas = new ArrayList<MerchantBean>();
        MerchantBean merchantBean1 = new MerchantBean();
        merchantBean1.setName("商家1");
        MerchantBean merchantBean2 = new MerchantBean();
        merchantBean2.setName("商家2");
        MerchantBean merchantBean3 = new MerchantBean();
        merchantBean3.setName("商家3");
        datas.add(merchantBean1);
        datas.add(merchantBean2);
        datas.add(merchantBean3);
    }
}
