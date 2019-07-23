package com.hkkj.carmall.user.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hkkj.carmall.R;
import com.hkkj.carmall.user.bean.WcardsBean;
import com.hkkj.carmall.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 李大为 on 2019/6/26.
 */

public class WcardsAdapter extends BaseQuickAdapter<WcardsBean,BaseViewHolder> {
    public Integer type;

    public WcardsAdapter(@LayoutRes int layoutResId, @Nullable List<WcardsBean> data, Integer type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, WcardsBean item) {
        String typeStr;
        if (type == 1){
            typeStr = "到期时间";
        }else {
            typeStr = "已过期";
        }
        String dateStr = TimeUtils.milliseconds2String(item.getEndTime(),new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        helper.setText(R.id.tv_wcard_name, item.getName()).setText(R.id.tv_wcard_staus, typeStr).setText(R.id.tv_valid_time, dateStr);
    }
}
