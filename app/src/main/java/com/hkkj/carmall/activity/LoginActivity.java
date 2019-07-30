package com.hkkj.carmall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hkkj.carmall.MainActivity;
import com.hkkj.carmall.MyApplication;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.UserInfoBean;
import com.hkkj.carmall.utils.AesUtil;
import com.hkkj.carmall.utils.Config;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.RegularUtils;
import com.hkkj.carmall.utils.ToastUtils;
import com.hkkj.carmall.utils.UtilSharedPreference;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class LoginActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.et_login_phone)
    EditText phone;

    @Bind(R.id.et_logion_pwd)
    EditText pwd;

    @Bind(R.id.tv_check_code)
    TextView checkCodeBtn;

    @Bind(R.id.tv_login_btn)
    TextView loginBtn;

    private String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logion);
        ButterKnife.bind(this);
        checkCodeBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == checkCodeBtn){

        }else if(v == loginBtn){
            phoneNum = phone.getText().toString().trim();
            String password = pwd.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(password)) {
                ToastUtils.showMessage("账号或验证码不能为空");
                return;
            }
            if (!RegularUtils.isMobileExact(phoneNum)) {
                ToastUtils.showMessage("手机号码格式不正确");
                return;
            }
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("mobile",phoneNum);
            params.put("smsCode",password);
            OkHttpUtils.post().params(params).url(Constants.LOGIN_URL).id(100).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("TAG", "联网失败" + e.getMessage());
                }

                @Override
                public void onResponse(String response, int id) {
                    switch (id) {
                        case 100:
                            if (response != null) {
                                //解析数据
                                processData(response);
                            }
                            break;
                        case 101:
                            break;
                    }
                }
            });
        }
    }

    private void processData(String json) {
        if (!TextUtils.isEmpty(json)) {
            JSONObject jsonObject = JSON.parseObject(json);
            //得到状态码
            String code = jsonObject.getString("code");
            if ("200".equals(code)){
                //解密token
                String tokenStr = AesUtil.aesDecryptString(jsonObject.getJSONObject("data").getString("token"));
                String token = tokenStr.split("_")[0];
                //保存token
                UtilSharedPreference.saveString(MyApplication.getInstance().getApplicationContext(), Config.TOKEN, token);
                UtilSharedPreference.saveString(MyApplication.getInstance().getApplicationContext(), Config.PHONE, phoneNum);
                //保存
                UserInfoBean userInfo = JSON.parseObject(jsonObject.getJSONObject("data").get("userInfo").toString(),UserInfoBean.class);
                MyApplication.getInstance().setUserInfoBean(userInfo);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else {
                ToastUtils.showMessage("登陆失败");
            }
        }
    }
}
