package cn.gr.entity;

import java.util.Date;

public class NmProduct {
    private Integer nmSku;

    private Integer msort;

    private Integer misort;

    private Integer brand;

    private Integer size;

    private Byte isFlaw;

    private String imgMain;

    private String title;

    private Integer marketPrice;

    private Integer salePrice;

    private Integer mySalePrice;

    private String flawDescribe;

    private String sku;

    private String skn;

    private String detail;

    private Integer stockNum;

    private Date updateTime;

    public Integer getNmSku() {
        return nmSku;
    }

    public void setNmSku(Integer nmSku) {
        this.nmSku = nmSku;
    }

    public Integer getMsort() {
        return msort;
    }

    public void setMsort(Integer msort) {
        this.msort = msort;
    }

    public Integer getMisort() {
        return misort;
    }

    public void setMisort(Integer misort) {
        this.misort = misort;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Byte getIsFlaw() {
        return isFlaw;
    }

    public void setIsFlaw(Byte isFlaw) {
        this.isFlaw = isFlaw;
    }

    public String getImgMain() {
        return imgMain;
    }

    public void setImgMain(String imgMain) {
        this.imgMain = imgMain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getMySalePrice() {
        return mySalePrice;
    }

    public void setMySalePrice(Integer mySalePrice) {
        this.mySalePrice = mySalePrice;
    }

    public String getFlawDescribe() {
        return flawDescribe;
    }

    public void setFlawDescribe(String flawDescribe) {
        this.flawDescribe = flawDescribe;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSkn() {
        return skn;
    }

    public void setSkn(String skn) {
        this.skn = skn;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}