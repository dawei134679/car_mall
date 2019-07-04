package com.hkkj.carmall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.hkkj.carmall.R;

public class MerchantPlatformActivity extends Activity {
    private ImageButton ibMpBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_platfrom);
        ibMpBack = (ImageButton) findViewById(R.id.ib_merchant_platform_back);
        ibMpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
