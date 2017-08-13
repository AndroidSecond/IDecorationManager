package com.haipeng.decoration.manager.model;

/**
 * Created by Administrator on 2017/8/13.
 */

public class TemplateModel {
    private long uniqueNumber;

    private String title;

    private String masterUniqueNumber;

    private String vendorUniqueNumber;

    private String imageAvator;


    public long getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(long uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMasterUniqueNumber() {
        return masterUniqueNumber;
    }

    public void setMasterUniqueNumber(String masterUniqueNumber) {
        this.masterUniqueNumber = masterUniqueNumber;
    }

    public String getVendorUniqueNumber() {
        return vendorUniqueNumber;
    }

    public void setVendorUniqueNumber(String vendorUniqueNumber) {
        this.vendorUniqueNumber = vendorUniqueNumber;
    }

    public String getImageAvator() {
        return imageAvator;
    }

    public void setImageAvator(String imageAvator) {
        this.imageAvator = imageAvator;
    }
}
