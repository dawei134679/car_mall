package com.hkkj.carmall.order.bean;

import java.util.List;

/**
 * Created by 李大为 on 2019/6/26.
 */

public class OrderBean {

    public Integer status =1;

    public List<SubOrderBean> subOrderList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SubOrderBean> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<SubOrderBean> subOrderList) {
        this.subOrderList = subOrderList;
    }
}
