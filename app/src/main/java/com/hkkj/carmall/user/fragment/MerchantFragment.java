package com.hkkj.carmall.user.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hkkj.carmall.R;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.user.adapter.CollectMerchantAdapter;
import com.hkkj.carmall.user.bean.CollectBean;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;


public class MerchantFragment extends BaseFragment {

    private RecyclerView rvMerchant;

    private List<CollectBean> datas;

    private CollectMerchantAdapter merchantAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_merchant, null);
        initData();
        rvMerchant = view.findViewById(R.id.rv_merchant);
        rvMerchant.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
        return view;
    }

    @Override
    public void initData() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("favoriteType",String.valueOf(1));
        OkHttpUtils
            .post()
            .headers(HeadersUtils.getHeaders(params))
            .params(params)
            .url(Constants.GET_FAVORITE_LIST)
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
                                        datas = JSON.parseArray(jsonObject.getJSONObject("data").get("list").toString(), CollectBean.class);
                                        merchantAdapter = new CollectMerchantAdapter(R.layout.item_merchant, datas);
                                        rvMerchant.setAdapter(merchantAdapter);
                                    }else {
                                        Log.e("e", "获取收藏商铺异常");
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
