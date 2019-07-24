package com.hkkj.carmall.home.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.hkkj.carmall.R;
import com.hkkj.carmall.activity.ShopActivity;
import com.hkkj.carmall.base.BaseFragment;
import com.hkkj.carmall.home.bean.ShopInfoBean;
import com.hkkj.carmall.utils.Constants;
import com.hkkj.carmall.utils.HeadersUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class ScarFragment extends BaseFragment implements AMapLocationListener, LocationSource {
    public Bundle savedInstanceState;
    private MapView mMapView;
    private AMap aMap = null;
    private MyLocationStyle myLocationStyle;
    private TextView tvAdress;

    //声明mListener对象，定位监听器
    private OnLocationChangedListener mListener = null;

    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    List<ShopInfoBean> shopInfos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;

    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_scar, null);
        tvAdress = getActivity().findViewById(R.id.tv_home_adress);


        mMapView = view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        //设置显示定位按钮 并且可以点击
        UiSettings settings = aMap.getUiSettings();
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。

        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色

        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色

        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        //初始化地图缩放比例
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15f));
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        // 设置定位监听
        aMap.setLocationSource(this);
        // 是否显示定位按钮
        settings.setMyLocationButtonEnabled(true);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                tvAdress.setText(aMapLocation.getAddress()+aMapLocation.getAdCode());

                //调后台接口,展示附近商铺
                getShopList(aMapLocation.getLatitude(),aMapLocation.getLongitude(),1);
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    public void getShopList(Double latitude, Double longitude, Integer serviceType) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("latitude",String.valueOf(latitude));
        params.put("longitude",String.valueOf(longitude));
        params.put("serviceType",String.valueOf(serviceType));
        OkHttpUtils
                .post()
                .headers(HeadersUtils.getHeaders(params))
                .params(params)
                .url(Constants.GET_SHOP_LIST)
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
                shopInfos = JSON.parseArray(jsonObject.getJSONObject("data").get("list").toString(), ShopInfoBean.class);
                if(shopInfos != null){
                    for(ShopInfoBean shopInfo : shopInfos){
                        LatLng latLng = new LatLng(shopInfo.getLatitude(), shopInfo.getLongitude());

                        MarkerOptions options = new MarkerOptions().position(latLng).title(shopInfo.getName())
                                .snippet("车胎非常好").icon(BitmapDescriptorFactory
                                        .fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.shop_icon)));

                        Marker marker = aMap.addMarker(options);
                        marker.setObject(shopInfo.getId());
                    }
                   
                    aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
                        // 定义 Marker 点击事件监听
                        // marker 对象被点击时回调的接口
                        // 返回 true 则表示接口已响应事件，否则返回false
                        @Override
                        public boolean onMarkerClick(Marker marker) {

                            Intent intent = new Intent(mContext, ShopActivity.class);
                            Object object = marker.getObject();
                            if (object != null){
                                intent.putExtra("shopId",object.toString());
                            }
                            startActivity(intent);
                            return false;
                        }
                    });
                }
            }else {
                Log.e("e", "获取小车列表失败");
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(mContext);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }
}