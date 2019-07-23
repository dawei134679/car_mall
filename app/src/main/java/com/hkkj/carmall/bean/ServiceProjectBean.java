package com.hkkj.carmall.bean;

import java.math.BigDecimal;

/**
 * Created by 李大为 on 2019/7/1.
 */

public class ServiceProjectBean {
    public Long serviceItemId;

    public  String serviceItemName;

    public BigDecimal serviceItemAmount;

    public Long getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(Long serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName;
    }

    public BigDecimal getServiceItemAmount() {
        return serviceItemAmount;
    }

    public void setServiceItemAmount(BigDecimal serviceItemAmount) {
        this.serviceItemAmount = serviceItemAmount;
    }
}
