package com.hkkj.carmall.user.fragment;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hkkj.carmall.R;
import com.hkkj.carmall.activity.EmployeePlatformActivity;
import com.hkkj.carmall.activity.GoodsInfoActivity;
import com.hkkj.carmall.activity.IncreasedTicketActivity;
import com.hkkj.carmall.activity.MerchantPlatformActivity;
import com.hkkj.carmall.activity.ShopActivity;
import com.hkkj.carmall.activity.ShopCartActivity;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.user.activity.CollectActivity;
import com.hkkj.carmall.user.activity.WarrantyCardActivity;


public class UserFragment extends BaseFragment implements View.OnClickListener {
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
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        findViews(view);
        return view ;
    }

    private void findViews(View view) {
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
            Intent intent = new Intent(mContext, ShopActivity.class);
            startActivity(intent);
            /*Toast.makeText(mContext, "轮胎抵扣卷", Toast.LENGTH_SHORT).show();
            Log.e("A","轮胎抵扣卷");*/
        }else if (v == tvUserZpzz ){
            Intent intent = new Intent(mContext, IncreasedTicketActivity.class);
            startActivity(intent);
            /*Toast.makeText(mContext, "增票资质", Toast.LENGTH_SHORT).show();
            Log.e("A","增票资质");*/
        }else if (v == tvUserFpgl ){
            Toast.makeText(mContext, "发票管理", Toast.LENGTH_SHORT).show();
            Log.e("A","发票管理");
        }else if (v == tvUserJjgz ){
            Intent intent = new Intent(mContext, GoodsInfoActivity.class);
            startActivity(intent);
            /*Toast.makeText(mContext, "计价规则", Toast.LENGTH_SHORT).show();
            Log.e("A","计价规则");*/
        }else if (v == tvUserYqhy ){
            Toast.makeText(mContext, "邀请好友", Toast.LENGTH_SHORT).show();
            Log.e("A","邀请好友");
        }else if (v == tvUserPtdh ){
            Intent intent = new Intent(mContext, ShopCartActivity.class);
            startActivity(intent);
            /*Toast.makeText(mContext, "平台电话", Toast.LENGTH_SHORT).show();
            Log.e("A","平台电话");*/
        }else if (v == tvUserLxpt ){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=532231254&version=1")));

           /* Toast.makeText(mContext, "联系平台", Toast.LENGTH_SHORT).show();
            Log.e("A","联系平台");*/
        }else if (v == tvUserSjgzt ){
            Intent intent = new Intent(mContext, MerchantPlatformActivity.class);
            startActivity(intent);
           /* Toast.makeText(mContext, "商家工作台", Toast.LENGTH_SHORT).show();
            Log.e("A","商家工作台");*/
        }else if (v == tvUserYggzt ){
            Intent intent = new Intent(mContext, EmployeePlatformActivity.class);
            startActivity(intent);
            /*Toast.makeText(mContext, "员工工作台", Toast.LENGTH_SHORT).show();
            Log.e("A","员工工作台");*/
        }
    }


}
