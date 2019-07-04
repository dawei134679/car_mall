package com.hkkj.carmall.user.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.hkkj.carmall.R;
import com.hkkj.carmall.user.adapter.WcardsAdapter;
import com.hkkj.carmall.user.bean.WcardsBean;

import java.util.ArrayList;
import java.util.List;

public class WarrantyCardActivity extends Activity {
    private RecyclerView rvWcards;
    private ImageButton ib_back;
    private List<WcardsBean> datas;
    private WcardsAdapter wcardsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty_card);
        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
        rvWcards = (RecyclerView) findViewById(R.id.rv_wcards);
        rvWcards.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        wcardsAdapter = new WcardsAdapter(R.layout.item_wcard, datas);
        rvWcards.setAdapter(wcardsAdapter);
    }

    private void initData() {
        datas = new ArrayList<WcardsBean>();
        WcardsBean wcardsBean1 = new WcardsBean();
        wcardsBean1.setCardName("大灯质保卡");
        WcardsBean wcardsBean2 = new WcardsBean();
        wcardsBean2.setCardName("轮胎质保卡");
        WcardsBean wcardsBean3 = new WcardsBean();
        wcardsBean3.setCardName("显示屏质保卡");
        datas.add(wcardsBean1);
        datas.add(wcardsBean2);
        datas.add(wcardsBean3);
    }
}
