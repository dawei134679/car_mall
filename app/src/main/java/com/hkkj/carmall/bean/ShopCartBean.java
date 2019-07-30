package com.hkkj.carmall.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李大为 on 2019/7/2.
 */

public class ShopCartBean {

    private Integer type;

    private List<ShopCartGoodBean> scgDatas = new ArrayList<>();

    private boolean checked = false;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<ShopCartGoodBean> getScgDatas() {
        return scgDatas;
    }

    public void setScgDatas(List<ShopCartGoodBean> scgDatas) {
        this.scgDatas = scgDatas;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
