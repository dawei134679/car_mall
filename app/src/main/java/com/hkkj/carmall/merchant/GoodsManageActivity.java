package com.hkkj.carmall.merchant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hkkj.carmall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GoodsManageActivity extends Activity implements View.OnClickListener  {
    @Bind(R.id.ib_goods_manage_back)
    ImageButton gmBack;

    @Bind(R.id.rl_gm_goods_describe)
    RelativeLayout gmDescribe;

    @Bind(R.id.rl_gm_goods_picture)
    RelativeLayout gmPicture;

    @Bind(R.id.rl_gm_goods_priceAndStandard)
    RelativeLayout gmPs;

    @Bind(R.id.rl_gm_goods_type)
    RelativeLayout gmType;

    @Bind(R.id.rl_gm_goods_zbService)
    RelativeLayout gmZbService;

    @Bind(R.id.tv_gm_submit)
    TextView gmSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_manage);
        ButterKnife.bind(this);
        initClick();
    }
    private void initClick() {
        gmBack.setOnClickListener(this);
        gmDescribe.setOnClickListener(this);
        gmPicture.setOnClickListener(this);
        gmPs.setOnClickListener(this);
        gmType.setOnClickListener(this);
        gmZbService.setOnClickListener(this);
        gmSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == gmBack) {
            finish();
        }else if (v == gmDescribe ){
            Toast.makeText(this, "商品描述", Toast.LENGTH_SHORT).show();
        }else if (v == gmPicture ){
            /*Intent intent = new Intent(this, GoodsManageActivity.class);
            startActivity(intent);*/
            Toast.makeText(this, "商品图片", Toast.LENGTH_SHORT).show();
        }else if (v == gmPs ){
            Toast.makeText(this, "商品价格型号", Toast.LENGTH_SHORT).show();
        }else if (v == gmType ){
            Toast.makeText(this, "商品类别", Toast.LENGTH_SHORT).show();
        } else if (v == gmZbService ){
            Toast.makeText(this, "是否有质保服务", Toast.LENGTH_SHORT).show();
        }else if (v == gmSubmit ){
            Toast.makeText(this, "提交", Toast.LENGTH_SHORT).show();
        }
    }
}
