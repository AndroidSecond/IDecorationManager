package com.haipeng.decoration.manager.model;


public class RecommendModel {


    private String title;

    private String imageAvator;

    // 团队，案例，供应商。
    // 不同的类型APP类型详情
    private String type;

    private long uniqueNumber;

    private String masterUniqueNumber;

    private String OrderUniqueNumber;

    private String VendorUniqueNumber;

    // 当，不是不同类型的时候，就会进入url页面。动态网页
    private String url;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public long getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(long uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageAvator() {
        return imageAvator;
    }

    public void setImageAvator(String imageAvator) {
        this.imageAvator = imageAvator;
    }

    public String getMasterUniqueNumber() {
        return masterUniqueNumber;
    }

    public void setMasterUniqueNumber(String masterUniqueNumber) {
        this.masterUniqueNumber = masterUniqueNumber;
    }

    public String getOrderUniqueNumber() {
        return OrderUniqueNumber;
    }

    public void setOrderUniqueNumber(String orderUniqueNumber) {
        OrderUniqueNumber = orderUniqueNumber;
    }

    public String getVendorUniqueNumber() {
        return VendorUniqueNumber;
    }

    public void setVendorUniqueNumber(String vendorUniqueNumber) {
        VendorUniqueNumber = vendorUniqueNumber;
    }
}
