package com.hkkj.carmall.bean;

import java.math.BigDecimal;

/**
 * Created by 李大为 on 2019/7/1.
 */

public class ServiceProjectBean {
    private Long serviceItemId;

    private Integer num = 0;

    private Integer status;

    private  String serviceItemName;

    private BigDecimal serviceItemAmount;



    public Long getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(Long serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
