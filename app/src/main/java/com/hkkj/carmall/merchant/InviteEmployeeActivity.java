package com.hkkj.carmall.merchant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.hkkj.carmall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InviteEmployeeActivity extends Activity {

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
    }
}
