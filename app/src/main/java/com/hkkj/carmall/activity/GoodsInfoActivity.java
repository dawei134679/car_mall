package com.hkkj.carmall.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.CommodityBean;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.GlideImageLoader;
import com.hkkj.carmall.utils.GlideImageUtils;
import com.hkkj.carmall.utils.HeadersUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class GoodsInfoActivity extends Activity {

    @Bind(R.id.b_goods_info_hBanner)
    Banner banner;

    @Bind(R.id.tv_goods_price)
    TextView goodPrice;

    @Bind(R.id.tv_goods_describe)
    TextView goodsDescribe;

    @Bind(R.id.tv_goods_format)
    TextView goodsFormat;

    @Bind(R.id.tv_goods_shop_name)
    TextView shopName;

    @Bind(R.id.iv_shop_icon)
    ImageView shopIcon;



    private  String commodityId;
    private  String commodityFormatId;
    private CommodityBean commodityInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        commodityId = getIntent().getStringExtra("commodityId");
        commodityFormatId = getIntent().getStringExtra("commodityFormatId");
        getCommodityInfo();
    }

    @OnClick(R.id.ib_goods_info_back)
    void goBack(){
        finish();
    }

    @OnClick(R.id.tv_goods_goShop)
    void goShop(){
        Intent intent = new Intent(this, ShopActivity.class);
        intent.putExtra("shopId",commodityInfo.getShopId().toString());
        startActivity(intent);
    }

    //商品收藏
    @OnClick(R.id.tv_goods_collect)
    void goodsCollect(){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("favoriteType",String.valueOf(2));
        params.put("favoriteId",String.valueOf(commodityInfo.getCommodityId()));
        params.put("commodityFormatId",String.valueOf(commodityInfo.getCommodityFormatId()));
        if (commodityInfo.getFavorite() == 0){
            OkHttpUtils
                    .post()
                    .headers(HeadersUtils.getHeaders(params))
                    .params(params)
                    .url(Constants.FAVORITE)
                    .id(100)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("TAG", "联网失败" + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            switch (id) {
                                case 100:
                                    if (response != null) {
                                        if (!TextUtils.isEmpty(response)) {
                                            JSONObject jsonObject = JSON.parseObject(response);
                                            //得到状态码
                                            String code = jsonObject.getString("code");
                                            if ("200".equals(code)){
                                                //修改收藏图片
                                                collectShop(true);
                                            }else {
                                                Log.e("e", "收藏商品异常");
                                            }
                                        }
                                    }
                                    break;
                                case 101:
                                    break;
                            }
                        }
                    });
        }else {
            OkHttpUtils
                    .post()
                    .headers(HeadersUtils.getHeaders(params))
                    .params(params)
                    .url(Constants.CANCEL_FAVORITE)
                    .id(100)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("TAG", "联网失败" + e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            switch (id) {
                                case 100:
                                    if (response != null) {
                                        if (!TextUtils.isEmpty(response)) {
                                            JSONObject jsonObject = JSON.parseObject(response);
                                            //得到状态码
                                            String code = jsonObject.getString("code");
                                            if ("200".equals(code)){
                                                //修改收藏图片
                                                collectShop(false);
                                            }else {
                                                Log.e("e", "取消收藏商品异常");
                                            }
                                        }
                                    }
                                    break;
                                case 101:
                                    break;
                            }
                        }
                    });
        }
    }
    private void collectShop(boolean type) {
        TextView collect = findViewById(R.id.tv_goods_collect);
        Drawable drawableLeft;
        if (type){
            drawableLeft = getResources().getDrawable(R.drawable.collection_icon_yes);
            commodityInfo.setFavorite(1);
        }else {
            drawableLeft = getResources().getDrawable(R.drawable.collection_icon_no);
            commodityInfo.setFavorite(0);
        }
        collect.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
    }


    //商品购买
    @OnClick(R.id.tv_goods_buy)
    void goodsBuy(){

    }

    //选择商品规格
    @OnClick(R.id.ll_goods_format_select)
    void selectFormat(){

    }


    //获取商品详情
   private void getCommodityInfo() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("commodityId",commodityId);
        params.put("commodityFormatId",commodityFormatId);
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.GET_COMMODITY_BY_ID)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        switch (id) {
                            case 100:
                                if (response != null) {
                                    if (!TextUtils.isEmpty(response)) {
                                        JSONObject jsonObject = JSON.parseObject(response);
                                        //得到状态码
                                        String code = jsonObject.getString("code");
                                        if ("200".equals(code)){
                                            commodityInfo = JSON.parseObject(jsonObject.get("data").toString(),CommodityBean.class);
                                            initView();
                                        }else {
                                            Log.e("e", "获取商品详情异常");
                                        }
                                    }
                                }
                                break;
                            case 101:
                                break;
                        }
                    }
                });
    }

    private void initView() {
        //设置商品轮播图
        setBannerOneDatas();
        goodPrice.setText("￥"+commodityInfo.getAmount());
        goodsFormat.setText(commodityInfo.getCommodityFormatName());
        goodsDescribe.setText(commodityInfo.getCommodityDescription());
        shopName.setText(commodityInfo.getShopName());
        GlideImageUtils.display(this,commodityInfo.getShopLogoUrl(),shopIcon);
        if(commodityInfo.getFavorite() ==  0){
            collectShop(false);
        }else{
            collectShop(true);
        }
    }

    private void setBannerOneDatas() {
        //设置图片小圆圈
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
//        banner.setImages(Arrays.asList(R.drawable.goods_info_icon,R.drawable.goods_info_icon));
        banner.setImages(commodityInfo.getImageList1());
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
