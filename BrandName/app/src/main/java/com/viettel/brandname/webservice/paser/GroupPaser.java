package com.viettel.brandname.webservice.paser;

import com.google.gson.annotations.SerializedName;
import com.viettel.brandname.model.AbsModel;
import com.viettel.brandname.model.GroupModel;

import java.util.ArrayList;

/**
 * Created by tulv2 on 5/26/2016.
 */
public class GroupPaser extends AbsModel {

    @SerializedName("pageTotal")
    private String pageTotal;

    @SerializedName("data")
    private ArrayList<GroupModel> data;

    public String getPageTotal() {
        return pageTotal;
    }

    public ArrayList<GroupModel> getData() {
        return data;
    }

    public void setPageTotal(String pageTotal) {
        this.pageTotal = pageTotal;
    }

    public void setData(ArrayList<GroupModel> data) {
        this.data = data;
    }
}
