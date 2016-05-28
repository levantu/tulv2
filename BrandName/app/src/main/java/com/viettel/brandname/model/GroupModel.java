package com.viettel.brandname.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tulv2 on 5/26/2016.
 */
public class GroupModel implements Serializable {

    @SerializedName("groupName")
    private String groupName;

    @SerializedName("phones")
    private String phones;

    @SerializedName("groupID")
    private long groupID;

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getPhones() {
        return phones;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }
}
