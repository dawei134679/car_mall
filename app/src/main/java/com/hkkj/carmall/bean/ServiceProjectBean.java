package com.hkkj.carmall.bean;

/**
 * Created by Administrator on 2019/7/1.
 */

public class ServiceProjectBean {
    public Integer id;

    public  String name;

    public Double price;

    public ServiceProjectBean(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ServiceProjectBean(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
