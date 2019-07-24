package com.hkkj.carmall.merchant;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hkkj.carmall.R;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.hkkj.carmall.utils.QRCodeUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class InviteEmployeeActivity extends Activity {

    private Context mContext = this;

    @Bind(R.id.ib_ie__back)
    ImageButton ieBack;

    @Bind(R.id.iv_ie_merchant_qrcode)
    ImageView ivMerchantQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_employee);
        ButterKnife.bind(this);
        ieBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        inviteEmployeesQrcode();
    }
    private void inviteEmployeesQrcode() {
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(null))
                .url(Constants.INVITE_EMPLOYEES_QRCODE)
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
                                            String qrcodeStr = jsonObject.get("data").toString();
                                            Bitmap qrCode = QRCodeUtil.createQRCode(qrcodeStr);
                                           ivMerchantQrcode.setImageBitmap(qrCode);
                                        }else {
                                            Log.e("e", "获取邀请二维码失败");
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
