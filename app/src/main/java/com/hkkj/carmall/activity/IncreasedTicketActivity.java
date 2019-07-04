package com.hkkj.carmall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.hkkj.carmall.R;

public class IncreasedTicketActivity extends Activity {

    private ImageButton ibItBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increased_ticket);
        ibItBack = (ImageButton) findViewById(R.id.ib_increased_ticket_back);
        ibItBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
