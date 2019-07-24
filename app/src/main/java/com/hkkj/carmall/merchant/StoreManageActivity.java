package com.hkkj.carmall.merchant;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hkkj.carmall.R;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;

public class StoreManageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manage);
        saveStoreInfo();
    }


    private void saveStoreInfo() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("shopId","0");
        params.put("name","大为店铺");
        params.put("moveService","1");
        params.put("address","大为家");
        params.put("provinceCode","130000 ");
        params.put("cityCode","130300");
        params.put("countyCode","130302");
        params.put("longitude","119.60");
        params.put("latitude","39.93");
        params.put("areaCode","1260");
        params.put("contactPeople","李大为");
        params.put("contactMobile","18603369235");
        params.put("serviceType","1");
        params.put("remarks","大为的店铺");
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.SAVE_SHOP_INFO)
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
                                            Log.e("e", "添加商铺信息失败");
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
