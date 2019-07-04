package com.hkkj.carmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/2.
 */

public class ShopCartBean {

    public Integer type;

    public List<ShopCarNameBean> scnDatas;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<ShopCarNameBean> getScnDatas() {
        return scnDatas;
    }

    public void setScnDatas(List<ShopCarNameBean> scnDatas) {
        this.scnDatas = scnDatas;
    }
}
