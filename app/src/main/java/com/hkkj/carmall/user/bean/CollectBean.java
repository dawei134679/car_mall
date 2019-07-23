package com.hkkj.carmall.user.bean;

import java.math.BigDecimal;

/**
 * Created by 李大为 on 2019/7/22.
 */

public class CollectBean {

    private Long commodityFormatId;

    private Long createTime;

    private Long favoriteId;

    private Integer favoriteType;

    private Long id;

    private Long userId;

    private String cover;

    private String title;

    private BigDecimal price;


    public Long getCommodityFormatId() {
        return commodityFormatId;
    }

    public void setCommodityFormatId(Long commodityFormatId) {
        this.commodityFormatId = commodityFormatId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Integer getFavoriteType() {
        return favoriteType;
    }

    public void setFavoriteType(Integer favoriteType) {
        this.favoriteType = favoriteType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
