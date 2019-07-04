package com.hkkj.carmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/7/2.
 */

public class ShopCarNameBean {
    public String name;

    public List<ShopCartGoodBean> sccDatas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShopCartGoodBean> getSccDatas() {
        return sccDatas;
    }

    public void setSccDatas(List<ShopCartGoodBean> sccDatas) {
        this.sccDatas = sccDatas;
    }
}
