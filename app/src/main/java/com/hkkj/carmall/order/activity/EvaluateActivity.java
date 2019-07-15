package com.hkkj.carmall.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hkkj.carmall.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class EvaluateActivity extends Activity {

    @Bind(R.id.ib_ae_back)
    ImageButton ibAeBack;

    @Bind(R.id.et_ae_order_evaluate)
    EditText orderEvaluate;

    @Bind(R.id.et_ae_order_spfw)
    MaterialRatingBar mrbSpfw;

    @Bind(R.id.et_ae_order_fwtd)
    MaterialRatingBar mrbFwtd;

    @Bind(R.id.tv_ae_submit)
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        ibAeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mrbSpfw.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                Toast.makeText(EvaluateActivity.this, "商品服务评分:"+String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });
        mrbFwtd.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                Toast.makeText(EvaluateActivity.this, "服务态度评分:"+String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EvaluateActivity.this, "提交评价", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
