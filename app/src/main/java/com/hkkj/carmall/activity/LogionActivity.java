package com.hkkj.carmall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hkkj.carmall.MainActivity;
import com.hkkj.carmall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogionActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.et_logion_phone)
    TextView phone;

    @Bind(R.id.et_logion_pwd)
    TextView password;

    @Bind(R.id.tv_check_code)
    TextView checkCodeBtn;

    @Bind(R.id.tv_logoin_btn)
    TextView logionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logion);
        ButterKnife.bind(this);
        checkCodeBtn.setOnClickListener(this);
        logionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == checkCodeBtn){

        }else if(v == logionBtn){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
