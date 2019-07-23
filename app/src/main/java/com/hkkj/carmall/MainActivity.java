package com.hkkj.carmall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hkkj.carmall.activity.LoginActivity;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.home.fragment.HomeFragment;
import com.hkkj.carmall.order.fragment.OrderFragment;
import com.hkkj.carmall.user.fragment.UserFragment;
import com.hkkj.carmall.utils.Config;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.hkkj.carmall.utils.UtilSharedPreference;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MainActivity extends FragmentActivity {
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_order)
    RadioButton rbOrder;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private BaseFragment mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getUserInfo();
        initFragment();
        initListener();
    }
    private void getUserInfo() {

        String token = UtilSharedPreference.getStringValue(MyApplication.getInstance().getApplicationContext(), Config.TOKEN);
        if (token == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else {
            OkHttpUtils
                    .post()
                    .headers(HeadersUtils.getHeaders(null))
                    .url(Constants.GET_USER_INFO)
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
            Integer code = Integer.valueOf(jsonObject.getString("code"));
            if(code > 4000){
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }


    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new OrderFragment());
        fragments.add(new UserFragment());
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_order:
                        position = 1;
                        break;
                    case R.id.rb_user:
                        position = 2;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(mContext, baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }


    /**
     *
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (mContext != nextFragment) {
            mContext = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

}
