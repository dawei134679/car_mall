package com.hkkj.carmall.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 李大为 on 2019/7/20.
 */

public class CommodityBean {

    public  Long commodityId;

    public  String commodityName;

    public BigDecimal amount;

    public  String commodityDescription;

    public  Long commodityFormatId;

    public  String commodityFormatName;

    public  String commodityImageUrl;

    public  Long shopId;

    public  String shopLogoUrl;

    public  String shopName;

    private Integer favorite;

    private List<String> imageList1;

    private List<String> imageList2;

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public Long getCommodityFormatId() {
        return commodityFormatId;
    }

    public void setCommodityFormatId(Long commodityFormatId) {
        this.commodityFormatId = commodityFormatId;
    }

    public String getCommodityFormatName() {
        return commodityFormatName;
    }

    public void setCommodityFormatName(String commodityFormatName) {
        this.commodityFormatName = commodityFormatName;
    }

    public String getCommodityImageUrl() {
        return commodityImageUrl;
    }

    public void setCommodityImageUrl(String commodityImageUrl) {
        this.commodityImageUrl = commodityImageUrl;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopLogoUrl() {
        return shopLogoUrl;
    }

    public void setShopLogoUrl(String shopLogoUrl) {
        this.shopLogoUrl = shopLogoUrl;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public List<String> getImageList1() {
        return imageList1;
    }

    public void setImageList1(List<String> imageList1) {
        this.imageList1 = imageList1;
    }

    public List<String> getImageList2() {
        return imageList2;
    }

    public void setImageList2(List<String> imageList2) {
        this.imageList2 = imageList2;
    }
}
