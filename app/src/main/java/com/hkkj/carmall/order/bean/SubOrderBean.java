package com.hkkj.carmall.order.bean;

/**
 * Created by Administrator on 2019/6/26.
 */
public class SubOrderBean {
    private String imageUrl;
    private String describe = "嘉实多";
    private double price = 10.0;
    private String specification = "规格1*8";
    private int num = 10;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
