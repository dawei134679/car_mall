package com.hkkj.carmall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.hkkj.carmall.MyApplication;
import com.hkkj.carmall.R;
import com.hkkj.carmall.adapter.ServiceProjectAdapter;
import com.hkkj.carmall.bean.CategoryBean;
import com.hkkj.carmall.bean.CommodityBean;
import com.hkkj.carmall.bean.SidOfNumBean;
import com.hkkj.carmall.bean.ServiceProjectBean;
import com.hkkj.carmall.home.bean.ShopInfoBean;
import com.hkkj.carmall.user.adapter.CommodityAdapter;
import com.hkkj.carmall.utils.Config;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.GlideImageLoader;
import com.hkkj.carmall.utils.HeadersUtils;
import com.hkkj.carmall.utils.StringUtils;
import com.hkkj.carmall.utils.ToastUtils;
import com.hkkj.carmall.utils.UtilSharedPreference;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.alibaba.fastjson.JSON.parseArray;

public class ShopActivity extends Activity {

    private  Context mContext = this;

    @Bind(R.id.b_shop_hBanner)
    Banner banner;

    @Bind(R.id.ib_shop_back)
    ImageButton ibShopBack;

    @Bind(R.id.et_shop_search)
    EditText search;

    @Bind(R.id.rv_shop_commodity)
    RecyclerView rvShopCommodity;

    private CommodityAdapter commodityAdapter;

    private List<CommodityBean> datas;

    @Bind(R.id.rv_shop_service_projects)
    RecyclerView rvShopServiceProjects;

    private ServiceProjectAdapter serviceProjectAdapter;

    private List<ServiceProjectBean> sdatas;

    private  String shopId;

    private ShopInfoBean shopInfo;

    private List<CommodityBean> hotDatas;

    List<String> imgUrls = new ArrayList<String>();

    List<SidOfNumBean> inDatas = new ArrayList<SidOfNumBean>();

    List<CategoryBean> cdatas = new ArrayList<CategoryBean>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        shopId = getIntent().getStringExtra("shopId");
        ibShopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    @OnClick(R.id.tv_shop_collect)
    void ShopCollect(){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("favoriteType",String.valueOf(1));
        params.put("favoriteId",String.valueOf(shopId));
        if (shopInfo.getFavorite() == 0){
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
                                                Log.e("e", "收藏店铺失败");
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
                                                Log.e("e", "取消收藏店铺失败");
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
        TextView collect = findViewById(R.id.tv_shop_collect);
        Drawable drawableLeft;
        if (type){
            drawableLeft = getResources().getDrawable(R.drawable.collection_icon_yes);
            shopInfo.setFavorite(1);
        }else {
            drawableLeft = getResources().getDrawable(R.drawable.collection_icon_no);
            shopInfo.setFavorite(0);
        }
        collect.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
    }


    private void initData() {

        getShopInfo();

        setBannerOneDatas();

        getCommodityList();

        getServiceProject();

        getCategoryList();
    }

    //获取商品分类
    private void getCategoryList() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("shopId",shopId);
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.get_category_ListBy_ShopId)
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
                                            cdatas = parseArray(jsonObject.get("data").toString(), CategoryBean.class);
                                        }else {
                                            Log.e("e", "获取商品分类列表详情异常");
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

    //获取服务项目列表
    private void getServiceProject() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("shopId",shopId);
        OkHttpUtils
            .post()
            .headers(HeadersUtils.getHeaders(params))
            .params(params)
            .url(Constants.SERVICE_ITEM_LIST)
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
                                        String sdatasStr = jsonObject.get("data").toString();

                                        //存服务列表
                                        UtilSharedPreference.saveString(MyApplication.getInstance().getApplicationContext(), Config.SERVICE_PROJECT_LIST, sdatasStr);

                                        sdatas = parseArray(sdatasStr, ServiceProjectBean.class);

                                        //初始化
                                        String cartListStr = UtilSharedPreference.getStringValue(MyApplication.getInstance().getApplicationContext(), Config.CART_SERVICE_LIST+shopId);
                                        if(!StringUtils.isEmpty(cartListStr)){
                                            inDatas = JSON.parseArray(cartListStr,SidOfNumBean.class);
                                            if(inDatas.size() > 0){
                                                for(SidOfNumBean inBean : inDatas){
                                                    for (ServiceProjectBean spBean : sdatas){
                                                        if (spBean.getServiceItemId() == inBean.getId()){
                                                            spBean.setNum(inBean.getNum());
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        serviceProjectAdapter = new ServiceProjectAdapter(R.layout.item_service_project,sdatas);
                                        rvShopServiceProjects.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
                                        rvShopServiceProjects.setAdapter(serviceProjectAdapter);
                                        rvShopServiceProjects.addOnItemTouchListener(new OnItemChildClickListener() {
                                            @Override
                                            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                            }

                                            @Override
                                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                                super.onItemChildClick(adapter, view, position);
                                                ServiceProjectBean serviceProjectItem = sdatas.get(position);
                                                LinearLayout parent = (LinearLayout)view.getParent();
                                                TextView tvNum = (TextView) parent.findViewById(R.id.tv_project_num);
                                                Integer num = Integer.valueOf(tvNum.getText().toString());
                                                int itemViewId = view.getId();

                                                SidOfNumBean idOfNumBean = new SidOfNumBean();

                                                switch (itemViewId){
                                                    case R.id.iv_shop_project_sub:
                                                        if (num == 0){
                                                            ToastUtils.showMessage("服务项目数量不能小于0");
                                                            break;
                                                        }
                                                        num--;
                                                        tvNum.setText(String.valueOf(num));
                                                        for (int i = 0; i < inDatas.size(); i++) {
                                                            if (inDatas.get(i).getId() == serviceProjectItem.getServiceItemId()){
                                                                if(num == 0){
                                                                    inDatas.remove(i);
                                                                }else{
                                                                    inDatas.get(i).setNum(num);
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        UtilSharedPreference.saveString(MyApplication.getInstance().getApplicationContext(), Config.CART_SERVICE_LIST+shopId, JSON.toJSONString(inDatas));
                                                        break;
                                                    case R.id.iv_shop_project_add:
                                                        num++;
                                                        tvNum.setText(String.valueOf(num));
                                                        //假设没有
                                                        boolean flag = true;
                                                        for (SidOfNumBean inBean : inDatas){
                                                            if (inBean.getId() ==serviceProjectItem.getServiceItemId()){
                                                                //修改本地购物车数量
                                                                inBean.setNum(num);
                                                                flag = false;
                                                                break;
                                                            }
                                                        }
                                                        if (flag){
                                                            //插入本地购物车新物品
                                                            idOfNumBean.setId(serviceProjectItem.getServiceItemId());
                                                            idOfNumBean.setNum(num);
                                                            inDatas.add(idOfNumBean);
                                                        }
                                                        UtilSharedPreference.saveString(MyApplication.getInstance().getApplicationContext(), Config.CART_SERVICE_LIST+shopId, JSON.toJSONString(inDatas));
                                                        break;
                                                }
                                    }
                                        });
                                    }else {
                                        Log.e("e", "获取店铺服务项目详情异常");
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

    //获取商品列表
    private void getCommodityList() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("shopId",shopId);
        OkHttpUtils
            .post()
            .headers(HeadersUtils.getHeaders(params))
            .params(params)
            .url(Constants.COMMODITY_LIST)
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
                                        datas = parseArray(jsonObject.getJSONObject("data").get("list").toString(), CommodityBean.class);
                                        commodityAdapter = new CommodityAdapter(R.layout.item_commodity, datas);
                                        rvShopCommodity.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
                                        rvShopCommodity.setAdapter(commodityAdapter);
                                        //设置点击事件
                                        rvShopCommodity.addOnItemTouchListener(new OnItemChildClickListener() {
                                            @Override
                                            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                            }

                                            @Override
                                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                                super.onItemChildClick(adapter, view, position);
                                                int itemViewId = view.getId();
                                                CommodityBean commodityItem = datas.get(position);
                                                switch (itemViewId){
                                                    case R.id.user_iv_commodity_imgurl:
                                                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                                                        intent.putExtra("commodityId",commodityItem.getCommodityId().toString());
                                                        intent.putExtra("commodityFormatId",commodityItem.getCommodityFormatId().toString());
                                                        startActivity(intent);
                                                        break;

                                                    case R.id.user_tv_commodity_buy:

                                                        break;
                                                }
                                            }
                                        });


                                    }else {
                                        Log.e("e", "店铺商品列表失败");
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

    //获取商铺详情
    private void getShopInfo() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id",shopId);
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.GET_SHOP_BY_ID)
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
                                    processData(response);
                                }
                                break;
                            case 101:
                                break;
                        }
                    }
                });
    }

    private void processData(String json) {
        if (!TextUtils.isEmpty(json)) {
            JSONObject jsonObject = JSON.parseObject(json);
            //得到状态码
            String code = jsonObject.getString("code");
            if ("200".equals(code)){
                shopInfo = JSON.parseObject(jsonObject.get("data").toString(), ShopInfoBean.class);
                //已收藏
                if(shopInfo.getFavorite() != 0){
                    collectShop(true);
                }
            }else {
                Log.e("e", "获取店铺详情失败");
            }
        }
    }

    //设置热门商品    https://blog.csdn.net/dorytmx/article/details/55259955
    private void setBannerOneDatas() {
        //获取热门商品
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("shopId",shopId);
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.COMMODITY__HOT_LIST)
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
                                            hotDatas = parseArray(jsonObject.get("data").toString(), CommodityBean.class);
                                            if (hotDatas != null){
                                                for (CommodityBean commodity : hotDatas){
                                                    imgUrls.add(commodity.getCommodityImageUrl());

                                                }
                                            }
                                            //设置图片小圆圈
                                            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                                            //设置图片加载器
                                            banner.setImageLoader(new GlideImageLoader());
                                            if(imgUrls.size()>0){
                                                banner.setImages(imgUrls);
                                            }else {
                                                banner.setImages(Arrays.asList(R.drawable.banner1,R.drawable.banner2,R.drawable.banner3));
                                            }
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
                                        }else {
                                            Log.e("e", "店铺热门商品详情");
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
