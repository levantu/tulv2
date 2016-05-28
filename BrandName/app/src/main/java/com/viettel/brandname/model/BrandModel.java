package com.viettel.brandname.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tulv2 on 5/26/2016.
 */
public class BrandModel implements Serializable {

    @SerializedName("brand_id")
    private String brandId;

    @SerializedName("brand_name")
    private String brandName;

    //1: active, 0: inactive, 2: deleted
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
