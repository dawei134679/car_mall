package com.hkkj.carmall.user.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hkkj.carmall.R;
import com.hkkj.carmall.user.adapter.WcardsAdapter;
import com.hkkj.carmall.user.bean.WcardsBean;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class WarrantyCardActivity extends Activity {

    @Bind(R.id.rv_wcards)
    RecyclerView rvWcards;

    private List<WcardsBean> datas;

    private WcardsAdapter wcardsAdapter;

    private Integer type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty_card);
        ButterKnife.bind(this);
        initData(1);
        rvWcards.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }
    @OnClick(R.id.ib_wc_back)
    void goBack(){
        finish();
    }

    @OnClick(R.id.rb_valid)
    void getValidList(){
        initData(1);
    }

    @OnClick(R.id.rb_unvalid)
    void getUnValidList(){
        initData(2);
    }

    private void initData(final Integer type) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("favoriteType",String.valueOf(type));
        OkHttpUtils
            .post()
            .headers(HeadersUtils.getHeaders(params))
            .params(params)
            .url(Constants.GET_MY_QACARD_LIST)
            .id(100)
            .build()
            .execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("TAG", "获取质保卡列表失败" + e.getMessage());
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
                                        datas = JSON.parseArray(jsonObject.getJSONObject("data").get("list").toString(), WcardsBean.class);
                                        wcardsAdapter = new WcardsAdapter(R.layout.item_wcard, datas, type);
                                        rvWcards.setAdapter(wcardsAdapter);
                                    }else {
                                        Log.e("e", "获取质保卡列表异常");
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
