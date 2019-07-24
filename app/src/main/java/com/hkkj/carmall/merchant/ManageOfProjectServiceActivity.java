package com.hkkj.carmall.merchant;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.ServiceProjectBean;
import com.hkkj.carmall.merchant.adapter.ManageOfServiceProjectAdapter;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.alibaba.fastjson.JSON.parseArray;

public class ManageOfProjectServiceActivity extends Activity {

    private Context mContext = this ;

    @Bind(R.id.ib_amps__back)
    ImageButton ampsBack;

    private List<ServiceProjectBean> sdatas = new ArrayList<ServiceProjectBean>();

    @Bind(R.id.rv_amps_projects)
    RecyclerView rvProjects;

    private ManageOfServiceProjectAdapter manageOfServiceProjectAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_of_project_service);
        ButterKnife.bind(this);
        ampsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCommodityList();
    }



    //获取商品列表
    private void getCommodityList() {
        OkHttpUtils
            .post()
            .headers(HeadersUtils.getHeaders(null))
            .url(Constants.GET_ALL_SERVIEC_ITEM_LIST)
            .id(100)
            .build()
            .execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("TAG", "联网失败" + e.getMessage());
                }

                @Override
                public void onResponse(String response, int id) {
                    switch (id) {
                        case 100:
                            if (response != null) {
                                if (!TextUtils.isEmpty(response)) {
                                    JSONObject jsonObject = JSON.parseObject(response);
                                    //得到状态码
                                    String code = jsonObject.getString("code");
                                    if ("200".equals(code)){
                                        sdatas = parseArray(jsonObject.get("data").toString(), ServiceProjectBean.class);
                                        rvProjects.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
                                        manageOfServiceProjectAdapter = new ManageOfServiceProjectAdapter(R.layout.item_manage_of_service_project,sdatas);
                                        rvProjects.setAdapter(manageOfServiceProjectAdapter);
                                        rvProjects.addOnItemTouchListener(new OnItemChildClickListener() {
                                            @Override
                                            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                            }
                                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                                super.onItemChildClick(adapter, view, position);
                                                int itemViewId = view.getId();
                                                ServiceProjectBean spBean = sdatas.get(position);
                                                switch (itemViewId){
                                                    case R.id.imosp_projectSwitch:
                                                        Integer status = 0;
                                                        if (spBean.getStatus() == 0){
                                                            status = 1;
                                                        }
                                                        updShopServiceItem(spBean.getServiceItemId(), status);
                                                        break;
                                                }
                                            }
                                        });
                                    }else {
                                        Log.e("e", "获取全部服务列表失败");
                                    }
                                }
                            }
                            break;
                        case 101:
                            break;
                    }
                }
            });
    }

    private void updShopServiceItem(Long serviceItemId, Integer status) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("serviceItemId",String.valueOf(serviceItemId));
        params.put("status",String.valueOf(status));
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.UPD_SHOPSERVICE_ITEM)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        switch (id) {
                            case 100:
                                if (response != null) {
                                    if (!TextUtils.isEmpty(response)) {
                                        JSONObject jsonObject = JSON.parseObject(response);
                                        //得到状态码
                                        String code = jsonObject.getString("code");
                                        if ("200".equals(code)){

                                        }else {
                                            Log.e("e", "修改服务项目失败");
                                        }
                                    }
                                }
                                break;
                            case 101:
                                break;
                        }
                    }
                });
    }

}
