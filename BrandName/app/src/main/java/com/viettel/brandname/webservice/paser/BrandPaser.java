package com.viettel.brandname.webservice.paser;

import com.google.gson.annotations.SerializedName;
import com.viettel.brandname.model.AbsModel;
import com.viettel.brandname.model.BrandModel;

import java.util.ArrayList;

/**
 * Created by tulv2 on 5/26/2016.
 */
public class BrandPaser extends AbsModel {
    @SerializedName("data")
    private ArrayList<BrandModel> data;

    public ArrayList<BrandModel> getData() {
        return data;
    }

    public void setData(ArrayList<BrandModel> data) {
        this.data = data;
    }
}
