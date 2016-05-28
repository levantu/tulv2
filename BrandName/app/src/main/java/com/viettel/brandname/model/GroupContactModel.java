package com.viettel.brandname.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tulv2 on 5/25/2016.
 */
public class GroupContactModel {

    @SerializedName("CP_GROUP_ID")
    private int id;

    @SerializedName("CP_ID")
    private int cpId;

    @SerializedName("CP_CODE")
    private String cpCode;

    @SerializedName("CP_GROUP_NAME")
    private String cpGroupName;

    @SerializedName("NOTE")
    private String note;

    public int getId() {
        return id;
    }

    public int getCpId() {
        return cpId;
    }

    public String getCpCode() {
        return cpCode;
    }

    public String getCpGroupName() {
        return cpGroupName;
    }

    public String getNote() {
        return note;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public void setCpGroupName(String cpGroupName) {
        this.cpGroupName = cpGroupName;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }
}