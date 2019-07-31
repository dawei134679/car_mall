package com.hkkj.carmall.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.bean.ShopCartBean;
import com.hkkj.carmall.bean.ShopCartGoodBean;
import com.hkkj.carmall.utils.GlideImageUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李大为 on 2019/6/28.
 */

public class ShopCartGoodAdapter extends BaseQuickAdapter<ShopCartGoodBean,BaseViewHolder> {

    List<ShopCartBean> scbData  = new ArrayList<>();

    private CheckBox cbAll;
    private TextView tvAllPrice;


    public ShopCartGoodAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartGoodBean> data,List<ShopCartBean> scbData, CheckBox cbAll, TextView tvAllPrice) {
        super(layoutResId, data);
        this.scbData = scbData;
        this.cbAll = cbAll;
        this.tvAllPrice = tvAllPrice;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCartGoodBean item) {

        CheckBox gCheckBox = (CheckBox)helper.getView(R.id.cb_stcg_good_check);
        gCheckBox.setChecked(item.isChecked());
        String specificationStr;
        if(null == item.getSpecification()){
            specificationStr = "";
        }else{
            specificationStr = item.getSpecification();
        }
        helper.setText(R.id.iv_stcg_good_describe,item.getDescribe())
                .setText(R.id.iv_stcg_good_price,"￥"+item.getPrice().toString())
                .setText(R.id.iv_stcg_good_specification, specificationStr)
                .setText(R.id.tv_stcg_num, item.getNum().toString() );
        if(null != item.getImageUrl()){
            GlideImageUtils.display(mContext,item.getImageUrl(),(ImageView) helper.getView(R.id.iv_stcg_good));
        }

        //计算总价格
        BigDecimal allPrice = BigDecimal.valueOf(00.00);
        for (ShopCartBean bean : scbData){
            for (ShopCartGoodBean sbean : bean.getScgDatas()){
                if(sbean.isChecked()){
                    allPrice = allPrice.add(sbean.getPrice().multiply(BigDecimal.valueOf(sbean.getNum())));
                }
            }
        }
        tvAllPrice.setText(allPrice.toString());

        helper.addOnClickListener(R.id.iv_stcg_sub);
        helper.addOnClickListener(R.id.iv_stcg_add);

        final CheckBox gck = helper.getView(R.id.cb_stcg_good_check);
        gck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setChecked(gck.isChecked());
                boolean flag = true;
                for(ShopCartBean scBean : scbData){
                    for(ShopCartGoodBean scgBean : scBean.getScgDatas()){
                        if(!scgBean.isChecked()){
                            flag = false;
                            break;
                        }
                    }
                }
                cbAll.setChecked(flag);
                notifyDataSetChanged();
                //计算总价格
                BigDecimal allPrice = BigDecimal.valueOf(00.00);
                for (ShopCartBean bean : scbData){
                    for (ShopCartGoodBean sbean : bean.getScgDatas()){
                        if(sbean.isChecked()){
                            allPrice = allPrice.add(sbean.getPrice().multiply(BigDecimal.valueOf(sbean.getNum())));
                        }
                    }
                }
                tvAllPrice.setText(allPrice.toString());
            }
        });

    }
}
