package com.hkkj.carmall.bean;

/**
 * Created by 李大为 on 2019/7/11.
 */

public class UserInfoBean {
    private Long id;

    private String mobile;

    private String nickname;

    private String headImg;

    private Integer Integer;

    private Long shopId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public java.lang.Integer getInteger() {
        return Integer;
    }

    public void setInteger(java.lang.Integer integer) {
        Integer = integer;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
