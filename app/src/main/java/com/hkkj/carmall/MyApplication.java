package com.hkkj.carmall;

import android.app.Application;

import com.hkkj.carmall.bean.UserInfoBean;
import com.hkkj.carmall.utils.ToastUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class MyApplication extends Application {

    private static MyApplication myApp;

    private UserInfoBean userInfoBean;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        ZXingLibrary.initDisplayOpinion(this);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        ToastUtils.init(this);
    }
    public static MyApplication getInstance(){
        return myApp;
    }

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }
}
