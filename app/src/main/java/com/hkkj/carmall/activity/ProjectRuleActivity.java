package com.hkkj.carmall.activity;

import android.app.Activity;
import android.os.Bundle;
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

public class ProjectRuleActivity extends Activity {
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

    }
}
