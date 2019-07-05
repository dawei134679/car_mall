package com.hkkj.carmall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.hkkj.carmall.R;
import com.hkkj.carmall.adapter.ServiceProjectPriceAdapter;
import com.hkkj.carmall.bean.ServiceProjectBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProjectRuleActivity extends AppCompatActivity {
    @Bind(R.id.ib_project_rule_back)
    ImageButton ibPrBack;

    @Bind(R.id.rv_pra_spp)
    RecyclerView rvPraSpp;

    private List<ServiceProjectBean> datas;

    private ServiceProjectPriceAdapter sppAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_rule);
        ButterKnife.bind(this);
        ibPrBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
        rvPraSpp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        sppAdapter = new ServiceProjectPriceAdapter(R.layout.item_project_price, datas);
        rvPraSpp.setHasFixedSize(true); //禁止recyclerview滑动，避免和ScrollView冲突；
        rvPraSpp.setNestedScrollingEnabled(false); //禁止recyclerview滑动，避免和ScrollView冲突；
        rvPraSpp.setAdapter(sppAdapter);
    }

    private void initData() {
        datas = new ArrayList<ServiceProjectBean>();
        ServiceProjectBean serviceProjectBean1 = new ServiceProjectBean(1,"换备胎",30.0);
        ServiceProjectBean serviceProjectBean2 = new ServiceProjectBean(2,"补大胎",20.0);
        ServiceProjectBean serviceProjectBean3 = new ServiceProjectBean(3,"倒轮",20.0);
        ServiceProjectBean serviceProjectBean4 = new ServiceProjectBean(4,"换机油",30.0);
        ServiceProjectBean serviceProjectBean5 = new ServiceProjectBean(5,"换燃油",20.0);
        ServiceProjectBean serviceProjectBean6 = new ServiceProjectBean(6,"打黄油",20.0);
        ServiceProjectBean serviceProjectBean7 = new ServiceProjectBean(7,"补小胎",10.0);
        ServiceProjectBean serviceProjectBean8 = new ServiceProjectBean(8,"动平衡",40.0);
        ServiceProjectBean serviceProjectBean9 = new ServiceProjectBean(10,"充氮气",100.0);
        datas.add(serviceProjectBean1);
        datas.add(serviceProjectBean2);
        datas.add(serviceProjectBean3);
        datas.add(serviceProjectBean4);
        datas.add(serviceProjectBean5);
        datas.add(serviceProjectBean6);
        datas.add(serviceProjectBean7);
        datas.add(serviceProjectBean8);
        datas.add(serviceProjectBean9);
    }
}
