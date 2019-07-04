package com.hkkj.carmall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.hkkj.carmall.R;
import com.hkkj.carmall.adapter.ShopCartAdapter;
import com.hkkj.carmall.bean.ShopCarNameBean;
import com.hkkj.carmall.bean.ShopCartBean;
import com.hkkj.carmall.bean.ShopCartGoodBean;

import java.util.ArrayList;
import java.util.List;

public class ShopCartActivity extends Activity {

    private ImageButton ibShopBack;

    private RecyclerView rvShopCart;

    private ShopCartAdapter shopCartAdapter;

    private List<ShopCartBean> scDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        ibShopBack = findViewById(R.id.ib_shop_car_back);
        ibShopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();

        ShopCartGoodBean shopCartGoodBean1 = new ShopCartGoodBean();
        shopCartGoodBean1.setDescribe("商品1");
        ShopCartGoodBean shopCartGoodBean2 = new ShopCartGoodBean();
        shopCartGoodBean2.setDescribe("商品2");
        ShopCartGoodBean shopCartGoodBean3 = new ShopCartGoodBean();
        shopCartGoodBean3.setDescribe("商品3");
        List<ShopCartGoodBean> scgdatas = new ArrayList<ShopCartGoodBean>();
        scgdatas.add(shopCartGoodBean1);
        scgdatas.add(shopCartGoodBean2);
        scgdatas.add(shopCartGoodBean3);

        ShopCarNameBean shopCarNameBean1 = new ShopCarNameBean();
        shopCarNameBean1.setName("商家1");
        shopCarNameBean1.setSccDatas(scgdatas);
        ShopCarNameBean shopCarNameBean2 = new ShopCarNameBean();
        shopCarNameBean2.setName("商家2");
        shopCarNameBean2.setSccDatas(scgdatas);
        List<ShopCarNameBean> scnDatas = new ArrayList<>();
        scnDatas.add(shopCarNameBean1);
        scnDatas.add(shopCarNameBean2);

        ShopCartBean shopCartBean1 = new ShopCartBean();
        shopCartBean1.setType(1);
        shopCartBean1.setScnDatas(scnDatas);
        ShopCartBean shopCartBean2 = new ShopCartBean();
        shopCartBean2.setType(0);
        shopCartBean2.setScnDatas(scnDatas);
        scDatas = new ArrayList<ShopCartBean>();
        scDatas.add(shopCartBean1);
        scDatas.add(shopCartBean2);

        rvShopCart.setLayoutManager(new LinearLayoutManager(this,1,false));
        shopCartAdapter = new ShopCartAdapter(R.layout.item_shop_type, scDatas);
        rvShopCart.setAdapter(shopCartAdapter);
    }

    private void initView() {
        rvShopCart = findViewById(R.id.rv_shop_cart_goods);
    }
}
