package com.hkkj.carmall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hkkj.carmall.R;
import com.hkkj.carmall.merchant.GoodsManageActivity;
import com.hkkj.carmall.merchant.InviteEmployeeActivity;
import com.hkkj.carmall.merchant.ManageOfProjectServiceActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MerchantPlatformActivity extends Activity implements View.OnClickListener  {

    @Bind(R.id.ib_merchant_platform_back)
    ImageButton ibMpBack;

    @Bind(R.id.tv_mp_storeManage)
    TextView mpStore;

    @Bind(R.id.tv_mp_goodsManage)
    TextView mpDoods;

    @Bind(R.id.tv_mp_yqyg)
    TextView mpYqyg;

    @Bind(R.id.tv_mp_serviceManage)
    TextView mpService;

    @Bind(R.id.tv_mp_orderManage)
    TextView mpOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_platfrom);
        ButterKnife.bind(this);
        ibMpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initClick();
    }

    private void initClick() {
        ibMpBack.setOnClickListener(this);
        mpStore.setOnClickListener(this);
        mpDoods.setOnClickListener(this);
        mpYqyg.setOnClickListener(this);
        mpService.setOnClickListener(this);
        mpOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ibMpBack) {
            finish();
        }else if (v == mpStore ){
            Toast.makeText(this, "店铺管理", Toast.LENGTH_SHORT).show();
        }else if (v == mpDoods ){
            Intent intent = new Intent(this, GoodsManageActivity.class);
            startActivity(intent);
        }else if (v == mpYqyg ){
            Intent intent = new Intent(this, InviteEmployeeActivity.class);
            startActivity(intent);
        }else if (v == mpService ){
            Intent intent = new Intent(this, ManageOfProjectServiceActivity.class);
            startActivity(intent);
        } else if (v == mpOrder ){
            Toast.makeText(this, "订单管理", Toast.LENGTH_SHORT).show();
        }
    }
}
