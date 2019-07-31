package com.hkkj.carmall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.MyApplication;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.CidOfNumBean;
import com.hkkj.carmall.bean.ShopCartBean;
import com.hkkj.carmall.bean.ShopCartGoodBean;
import com.hkkj.carmall.bean.SidOfNumBean;
import com.hkkj.carmall.utils.Config;
import com.hkkj.carmall.utils.ToastUtils;
import com.hkkj.carmall.utils.UtilSharedPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李大为 on 2019/6/28.
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartBean,BaseViewHolder> {

    private ShopCartGoodAdapter shopCartGoodAdapter;
    private List<ShopCartBean> scbData;
    private CheckBox cbAll;
    private TextView tvAllPrice;
    List<ShopCartGoodBean> scgDatas;

    private String shopId;

    //购物车服务项目
    private List<SidOfNumBean> SDatas = new ArrayList<SidOfNumBean>();
    //购物车商品项目
    private List<CidOfNumBean> CDatas = new ArrayList<CidOfNumBean>();

    public ShopCartAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartBean> data, CheckBox cbAll, TextView tvAllPrice, List<SidOfNumBean> SDatas, List<CidOfNumBean> CDatas, String shopId) {
        super(layoutResId, data);
        scbData = data;
        this.cbAll = cbAll;
        this.tvAllPrice = tvAllPrice;
        this.SDatas = SDatas;
        this.CDatas = CDatas;
        this.shopId = shopId;

    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCartBean item) {
        String statusText = null;
        final Integer type  = item.getType();
        if(type == 0){
            statusText = "到店服务";
        }
        if(type == 1){
            statusText = "移动服务";
        }

        helper.setText(R.id.tv_shop_cartTypeName,statusText);
        RecyclerView rvRST = helper.getView(R.id.rv_shop_cart_type_cart);
        rvRST.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        final List<ShopCartGoodBean> scgDatas = item.getScgDatas();
        shopCartGoodAdapter = new ShopCartGoodAdapter(R.layout.item_shop_type_cart_goods,scgDatas, scbData, cbAll, tvAllPrice);
        rvRST.setAdapter(shopCartGoodAdapter);

        //添加点击事件
        rvRST.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                ShopCartGoodBean scgItem = scgDatas.get(position);
                RelativeLayout parent = (RelativeLayout)view.getParent();
                TextView tvNum = (TextView) parent.findViewById(R.id.tv_stcg_num);
                Integer num = Integer.valueOf(tvNum.getText().toString());
                int itemViewId = view.getId();

                switch (itemViewId){
                    case R.id.iv_stcg_sub:
                        if (num == 1){
                            ToastUtils.showMessage("该宝贝不能在少了");
                            break;
                        }
                        num = num - 1;
                        scgItem.setNum(num);
                        shopCartGoodAdapter.notifyDataSetChanged();
                        notifyDataSetChanged();
                        if (type == 1){
                            for (int i = 0; i < SDatas.size(); i++) {
                                if (SDatas.get(i).getId() == scgItem.getId()){
                                    SDatas.get(i).setNum(num);
                                    break;
                                }
                            }
                            UtilSharedPreference.saveString(MyApplication.getInstance(), Config.CART_SERVICE_LIST+shopId, JSON.toJSONString(SDatas));
                        }else{
                            for (int i = 0; i < CDatas.size(); i++) {
                                if (CDatas.get(i).getId() == scgItem.getId()){
                                    CDatas.get(i).setNum(num);
                                    break;
                                }
                            }
                            UtilSharedPreference.saveString(MyApplication.getInstance(), Config.CART_COMMODITY_LIST+shopId, JSON.toJSONString(CDatas));
                        }
                        break;
                    case R.id.iv_stcg_add:
                        num = num + 1;
                        scgItem.setNum(num);
                        shopCartGoodAdapter.notifyDataSetChanged();
                        notifyDataSetChanged();
                        if (type == 1){
                            for (int i = 0; i < SDatas.size(); i++) {
                                if (SDatas.get(i).getId() == scgItem.getId()){
                                    SDatas.get(i).setNum(num);
                                    break;
                                }
                            }
                            UtilSharedPreference.saveString(MyApplication.getInstance(), Config.CART_SERVICE_LIST+shopId, JSON.toJSONString(SDatas));
                        }else{
                            for (int i = 0; i < CDatas.size(); i++) {
                                if (CDatas.get(i).getId() == scgItem.getId()){
                                     CDatas.get(i).setNum(num);
                                    break;
                                }
                            }
                            UtilSharedPreference.saveString(MyApplication.getInstance(), Config.CART_COMMODITY_LIST+shopId, JSON.toJSONString(CDatas));
                        }
                        break;
                }
            }
        });
    /*    final CheckBox typeAll = (CheckBox)helper.getView(spc_cb_shop_all);
        typeAll.setChecked(item.isChecked());
        typeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShopCartGoodBean> scgDatas = item.getScgDatas();
                for (ShopCartGoodBean bean : scgDatas){
                    bean.setChecked(typeAll.isChecked());
                }
                shopCartTypeAdapter.notifyDataSetChanged();
                notifyDataSetChanged();
            }
        });
*/
    }
}
