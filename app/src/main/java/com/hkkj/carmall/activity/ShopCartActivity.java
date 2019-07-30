package com.hkkj.carmall.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hkkj.carmall.MyApplication;
import com.hkkj.carmall.R;
import com.hkkj.carmall.adapter.ShopCartAdapter;
import com.hkkj.carmall.bean.CidOfNumBean;
import com.hkkj.carmall.bean.CommodityBean;
import com.hkkj.carmall.bean.ServiceProjectBean;
import com.hkkj.carmall.bean.ShopCartBean;
import com.hkkj.carmall.bean.ShopCartGoodBean;
import com.hkkj.carmall.bean.SidOfNumBean;
import com.hkkj.carmall.utils.Config;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.hkkj.carmall.utils.StringUtils;
import com.hkkj.carmall.utils.UtilSharedPreference;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.alibaba.fastjson.JSON.parseArray;

public class ShopCartActivity extends Activity {

    private Context mContext = this;

    @Bind(R.id.spc_cb_all)
    CheckBox cbAll;

    @Bind(R.id.ib_shop_car_back)
    ImageButton ibShopBack;

    @Bind(R.id.rv_shop_cart_goods)
    RecyclerView rvShopCart;

    @Bind(R.id.tv_shop_car_all_price)
    TextView tvAllPrice;

    private String shopId;

    //购物车服务项目
    private List<SidOfNumBean> SDatas = new ArrayList<SidOfNumBean>();
    //购物车商品项目
    private List<CidOfNumBean> CDatas = new ArrayList<CidOfNumBean>();
    //全部商品
    private List<CommodityBean> allcDatas = new ArrayList<>();
    //全部服务项目
    private List<ServiceProjectBean> allSpDatas = new ArrayList<>();
    //全部购物车商品
    private List<ShopCartBean> scDatas = new ArrayList<>();

    private ShopCartAdapter shopCartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        ButterKnife.bind(this);
        shopId = getIntent().getStringExtra("shopId");
        ibShopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCommodityList();
    }


    //获取商品列表
    private void getCommodityList() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("shopId", shopId);
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
                                        if ("200".equals(code)) {
                                            allcDatas = JSON.parseArray(jsonObject.getJSONObject("data").get("list").toString(), CommodityBean.class);
                                            //获取购物车商品id列表
                                            String spcCids = UtilSharedPreference.getStringValue(getApplicationContext(), Config.CART_COMMODITY_LIST + shopId);
                                            if(!StringUtils.isEmpty(spcCids)){
                                                CDatas = JSON.parseArray(spcCids,CidOfNumBean.class);
                                                if(CDatas.size() > 0){
                                                    ShopCartBean shopCartBean = new ShopCartBean();
                                                    shopCartBean.setType(0);
                                                    ArrayList<ShopCartGoodBean> shopCartGoodBeens = new ArrayList<>();
                                                    //商品赋值
                                                    for (CidOfNumBean cnb : CDatas){
                                                        for(CommodityBean cb : allcDatas){
                                                            if (cnb.getId() == cb.getCommodityId()){
                                                                ShopCartGoodBean shopCartGoodBean = new ShopCartGoodBean();
                                                                shopCartGoodBean.setId(cb.getCommodityId());
                                                                shopCartGoodBean.setNum(cnb.getNum());
                                                                shopCartGoodBean.setImageUrl(cb.getCommodityImageUrl());
                                                                shopCartGoodBean.setDescribe(cb.getCommodityDescription());
                                                                shopCartGoodBean.setPrice(cb.getAmount());
                                                                shopCartGoodBean.setSpecification(cb.getCommodityFormatName());
                                                                shopCartGoodBeens.add(shopCartGoodBean);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    shopCartBean.setScgDatas(shopCartGoodBeens);
                                                    scDatas.add(shopCartBean) ;
                                                    //获取购物车全部服务项目
                                                    getServiceProject();
                                                }
                                            }
                                        } else {
                                            Log.e("e", "店铺全部商品列表失败");
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
                                            UtilSharedPreference.saveString(getApplicationContext(), Config.SERVICE_PROJECT_LIST, sdatasStr);

                                            allSpDatas = parseArray(sdatasStr, ServiceProjectBean.class);

                                            //初始化
                                            String cartListStr = UtilSharedPreference.getStringValue(MyApplication.getInstance().getApplicationContext(), Config.CART_SERVICE_LIST+shopId);
                                            if(!StringUtils.isEmpty(cartListStr)){
                                                SDatas = JSON.parseArray(cartListStr,SidOfNumBean.class);
                                                if(SDatas.size() > 0){
                                                    ShopCartBean shopCartBean = new ShopCartBean();
                                                    shopCartBean.setType(1);
                                                    ArrayList<ShopCartGoodBean> shopCartGoodBeens = new ArrayList<>();
                                                    for(SidOfNumBean inBean : SDatas){
                                                        for (ServiceProjectBean spBean : allSpDatas){
                                                            if (spBean.getServiceItemId() == inBean.getId()){
                                                                ShopCartGoodBean shopCartGoodBean = new ShopCartGoodBean();
                                                                shopCartGoodBean.setId(inBean.getId());
                                                                shopCartGoodBean.setNum(inBean.getNum());
                                                                shopCartGoodBean.setImageUrl(null);
                                                                shopCartGoodBean.setDescribe(spBean.getServiceItemName());
                                                                shopCartGoodBean.setPrice(spBean.getServiceItemAmount());
                                                                shopCartGoodBeens.add(shopCartGoodBean);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    shopCartBean.setScgDatas(shopCartGoodBeens);
                                                    scDatas.add(shopCartBean) ;
                                                }
                                            }
                                            shopCartAdapter = new ShopCartAdapter(R.layout.item_shop_type_cart,scDatas,cbAll,tvAllPrice,SDatas,CDatas,shopId);
                                            rvShopCart.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false));
                                            rvShopCart.setAdapter(shopCartAdapter);
                                            cbAll.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   for (ShopCartBean bean : scDatas){
                                                       /*bean.setChecked(cbAll.isChecked());*/
                                                       for (ShopCartGoodBean sbean : bean.getScgDatas()){
                                                           sbean.setChecked(cbAll.isChecked());
                                                       }
                                                   }
                                                   shopCartAdapter.notifyDataSetChanged();
                                                   //计算总价格
                                                   BigDecimal allPrice = BigDecimal.valueOf(00.00);
                                                   for (ShopCartBean bean : scDatas){
                                                       for (ShopCartGoodBean sbean : bean.getScgDatas()){
                                                           if(sbean.isChecked()){
                                                               allPrice = allPrice.add(sbean.getPrice().multiply(BigDecimal.valueOf(sbean.getNum())));
                                                           }
                                                       }
                                                   }
                                                   tvAllPrice.setText(allPrice.toString());
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

}