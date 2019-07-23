package com.hkkj.carmall.merchant;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.ServiceProjectBean;
import com.hkkj.carmall.merchant.adapter.ManageOfServiceProjectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ManageOfProjectServiceActivity extends Activity {

    @Bind(R.id.ib_amps__back)
    ImageButton ampsBack;

    private List<ServiceProjectBean> sdatas;

    @Bind(R.id.rv_amps_projects)
    RecyclerView rvProjects;

    private ManageOfServiceProjectAdapter manageOfServiceProjectAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_of_project_service);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        rvProjects.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        manageOfServiceProjectAdapter = new ManageOfServiceProjectAdapter(R.layout.item_manage_of_service_project,sdatas);
        rvProjects.setAdapter(manageOfServiceProjectAdapter);
    }

    private void initData() {
        ampsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sdatas = new ArrayList<ServiceProjectBean>();
        /*ServiceProjectBean serviceProjectBean1 = new ServiceProjectBean(1,"换备胎");
        ServiceProjectBean serviceProjectBean2 = new ServiceProjectBean(2,"补大胎");
        ServiceProjectBean serviceProjectBean3 = new ServiceProjectBean(3,"倒轮");
        ServiceProjectBean serviceProjectBean4 = new ServiceProjectBean(4,"换机油");
        ServiceProjectBean serviceProjectBean5 = new ServiceProjectBean(5,"换燃油");
        ServiceProjectBean serviceProjectBean6 = new ServiceProjectBean(6,"打黄油");
        ServiceProjectBean serviceProjectBean7 = new ServiceProjectBean(7,"补小胎");
        ServiceProjectBean serviceProjectBean8 = new ServiceProjectBean(8,"动平衡");
        ServiceProjectBean serviceProjectBean9 = new ServiceProjectBean(10,"充氮气");
        sdatas.add(serviceProjectBean1);
        sdatas.add(serviceProjectBean2);
        sdatas.add(serviceProjectBean3);
        sdatas.add(serviceProjectBean4);
        sdatas.add(serviceProjectBean5);
        sdatas.add(serviceProjectBean6);
        sdatas.add(serviceProjectBean7);
        sdatas.add(serviceProjectBean8);
        sdatas.add(serviceProjectBean9);*/
    }
}
