package com.hkkj.carmall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.hkkj.carmall.R;
import com.hkkj.carmall.adapter.ServiceProjectAdapter;
import com.hkkj.carmall.bean.ServiceProjectBean;
import com.hkkj.carmall.user.adapter.CommodityAdapter;
import com.hkkj.carmall.user.bean.CommodityBean;
import com.hkkj.carmall.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopActivity extends Activity {

    private Banner banner;

    private ImageButton ibShopBack;

    private RecyclerView rvShopCommodity;

    private CommodityAdapter commodityAdapter;

    private List<CommodityBean> datas;

    private RecyclerView rvShopServiceProjects;

    private ServiceProjectAdapter serviceProjectAdapter;

    private List<ServiceProjectBean> sdatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        banner = (Banner) findViewById(R.id.b_shop_hBanner);
        ibShopBack = findViewById(R.id.ib_shop_back);
        ibShopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initData();
        rvShopCommodity = findViewById(R.id.rv_shop_commodity);
        rvShopCommodity.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        commodityAdapter = new CommodityAdapter(R.layout.item_commodity, datas);
        rvShopCommodity.setAdapter(commodityAdapter);

        rvShopServiceProjects = findViewById(R.id.rv_shop_service_projects);
        rvShopServiceProjects.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        serviceProjectAdapter = new ServiceProjectAdapter(R.layout.item_service_project,sdatas);
        rvShopServiceProjects.setAdapter(serviceProjectAdapter);
        setBannerOneDatas();
    }

    private void initData() {
        datas = new ArrayList<CommodityBean>();
        CommodityBean commodityBean1 = new CommodityBean();
        commodityBean1.setDescribe("商品1");
        commodityBean1.setPrice(555.0);
        CommodityBean commodityBean2 = new CommodityBean();
        commodityBean2.setDescribe("商品2");
        commodityBean2.setPrice(100.0);
        CommodityBean commodityBean3 = new CommodityBean();
        commodityBean3.setDescribe("商品3");
        commodityBean3.setPrice(55.0);
        CommodityBean commodityBean4 = new CommodityBean();
        commodityBean4.setDescribe("商品4");
        commodityBean4.setPrice(55.0);
        CommodityBean commodityBean5 = new CommodityBean();
        commodityBean5.setDescribe("商品5");
        commodityBean5.setPrice(55.0);
        datas.add(commodityBean1);
        datas.add(commodityBean2);
        datas.add(commodityBean3);
        datas.add(commodityBean4);
        datas.add(commodityBean5);
        datas.add(commodityBean5);
        datas.add(commodityBean5);
        sdatas = new ArrayList<ServiceProjectBean>();
        ServiceProjectBean serviceProjectBean1 = new ServiceProjectBean(1,"换备胎");
        ServiceProjectBean serviceProjectBean2 = new ServiceProjectBean(2,"补打胎");
        ServiceProjectBean serviceProjectBean3 = new ServiceProjectBean(3,"倒轮");
        ServiceProjectBean serviceProjectBean4 = new ServiceProjectBean(4,"换机油");
        ServiceProjectBean serviceProjectBean5 = new ServiceProjectBean(5,"换燃油");
        ServiceProjectBean serviceProjectBean6 = new ServiceProjectBean(6,"打黄油");
        ServiceProjectBean serviceProjectBean7 = new ServiceProjectBean(7,"补小胎");
        ServiceProjectBean serviceProjectBean8 = new ServiceProjectBean(8,"动平衡");
        ServiceProjectBean serviceProjectBean9 = new ServiceProjectBean(10,"充氮气");
        sdatas.add(serviceProjectBean1);
        sdatas.add(serviceProjectBean2);
        sdatas.add(serviceProjectBean3);
        sdatas.add(serviceProjectBean4);
        sdatas.add(serviceProjectBean5);
        sdatas.add(serviceProjectBean6);
        sdatas.add(serviceProjectBean7);
        sdatas.add(serviceProjectBean8);
        sdatas.add(serviceProjectBean9);
    }

    private void setBannerOneDatas() {
        //设置图片小圆圈
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(Arrays.asList(R.drawable.banner1,R.drawable.banner2,R.drawable.banner3));
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
