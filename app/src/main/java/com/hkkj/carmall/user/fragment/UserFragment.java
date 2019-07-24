package com.hkkj.carmall.user.fragment;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hkkj.carmall.R;
import com.hkkj.carmall.activity.EmployeePlatformActivity;
import com.hkkj.carmall.activity.IncreasedTicketActivity;
import com.hkkj.carmall.activity.LoginActivity;
import com.hkkj.carmall.activity.MerchantPlatformActivity;
import com.hkkj.carmall.activity.ProjectRuleActivity;
import com.hkkj.carmall.activity.SelectPhotoActivity;
import com.hkkj.carmall.activity.ShopCartActivity;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.order.activity.EvaluateActivity;
import com.hkkj.carmall.user.activity.CollectActivity;
import com.hkkj.carmall.user.activity.WarrantyCardActivity;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.hkkj.carmall.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


public class UserFragment extends BaseFragment implements View.OnClickListener {

    private ImageView userIcon;
    private TextView tvUserCollect;
    private TextView tvUserZbk;
    private TextView tvUserDkj;
    private TextView tvUserZpzz;
    private TextView tvUserFpgl;
    private TextView tvUserJjgz;
    private TextView tvUserYqhy;
    private TextView tvUserPtdh;
    private TextView tvUserLxpt;
    private TextView tvUserSjgzt;
    private TextView tvUserYggzt;
    private Button userLogoinOut;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        findViews(view);
        return view ;
    }

    private void findViews(View view) {
        userIcon =view.findViewById(R.id.ib_user_icon);
        tvUserCollect = view.findViewById(R.id.tv_user_collect);
        tvUserZbk = view.findViewById(R.id.tv_user_zbk);
        tvUserDkj = view.findViewById(R.id.tv_user_dkj);
        tvUserZpzz = view.findViewById(R.id.tv_user_zpzz);
        tvUserFpgl = view.findViewById(R.id.tv_user_fpgl);
        tvUserJjgz = view.findViewById(R.id.tv_user_jjgz);
        tvUserYqhy = view.findViewById(R.id.tv_user_yqhy);
        tvUserPtdh = view.findViewById(R.id.tv_user_ptdh);
        tvUserLxpt = view.findViewById(R.id.tv_user_lxpt);
        tvUserSjgzt = view.findViewById(R.id.tv_user_sjgzt);
        tvUserYggzt = view.findViewById(R.id.tv_user_yggzt);
        userLogoinOut = view.findViewById(R.id.user_logoin_out);

//        GlideImageUtils.DisplayCircle(mContext,"http://img4.duitang.com/uploads/item/201209/20/20120920160424_QH2jt.thumb.600_0.jpeg",userIcon);


        userIcon.setOnClickListener(this);
        tvUserCollect.setOnClickListener(this);
        tvUserZbk.setOnClickListener(this);
        tvUserDkj.setOnClickListener(this);
        tvUserZpzz.setOnClickListener(this);
        tvUserFpgl.setOnClickListener(this);
        tvUserJjgz.setOnClickListener(this);
        tvUserYqhy.setOnClickListener(this);
        tvUserPtdh.setOnClickListener(this);
        tvUserLxpt.setOnClickListener(this);
        tvUserSjgzt.setOnClickListener(this);
        tvUserYggzt.setOnClickListener(this);
        userLogoinOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvUserCollect) {
            Intent intent = new Intent(mContext, CollectActivity.class);
            startActivity(intent);
        }else if (v == tvUserZbk ){
            Intent intent = new Intent(mContext, WarrantyCardActivity.class);
            startActivity(intent);
        }else if (v == tvUserDkj ){
            ToastUtils.showMessage("此功能暂未开放",5);;
        }else if (v == tvUserZpzz ){
            Intent intent = new Intent(mContext, IncreasedTicketActivity.class);
            startActivity(intent);
        }else if (v == tvUserFpgl ){
            ToastUtils.showMessage("此功能暂未开放",5);
        }else if (v == tvUserJjgz ){
            Intent intent = new Intent(mContext, ProjectRuleActivity.class);
            startActivity(intent);
        }else if (v == tvUserYqhy ){
            Intent intent = new Intent(mContext, EvaluateActivity.class);
            startActivity(intent);
        }else if (v == tvUserPtdh ){
            Intent intent = new Intent(mContext, ShopCartActivity.class);
            startActivity(intent);
        }else if (v == tvUserLxpt ){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=532231254&version=1")));
        }else if (v == tvUserSjgzt ){
            Intent intent = new Intent(mContext, MerchantPlatformActivity.class);
            startActivity(intent);
        }else if (v == tvUserYggzt ){
            Intent intent = new Intent(mContext, EmployeePlatformActivity.class);
            startActivity(intent);
        }else if (v == userLogoinOut ){
            //退出登录
            logout();
        }else if (v == userIcon ){
            Intent intent = new Intent(mContext, SelectPhotoActivity.class);
             startActivity(intent);
        }
    }
    public void logout() {
        OkHttpUtils.post().headers(HeadersUtils.getHeaders(null)).url(Constants.LOGOUT).id(100).build().execute(new StringCallback() {
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
    private void processData(String json) {
        if (!TextUtils.isEmpty(json)) {
            JSONObject jsonObject = JSON.parseObject(json);
            //得到状态码
            String code = jsonObject.getString("code");
            if ("200".equals(code)){
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }else {
                ToastUtils.showMessage("退出失败");
            }
        }
    }

}
