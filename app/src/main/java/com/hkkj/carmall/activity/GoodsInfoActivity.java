package com.hkkj.carmall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.hkkj.carmall.R;
import com.hkkj.carmall.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.Arrays;

public class GoodsInfoActivity extends Activity {
    private Banner banner;
    private ImageButton ibGoodsInfoBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        banner = (Banner) findViewById(R.id.b_goods_info_hBanner);
        ibGoodsInfoBack = findViewById(R.id.ib_goods_info_back);
        ibGoodsInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setBannerOneDatas();
    }
    private void setBannerOneDatas() {
        //设置图片小圆圈
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(Arrays.asList(R.drawable.goods_info_icon,R.drawable.goods_info_icon));
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
