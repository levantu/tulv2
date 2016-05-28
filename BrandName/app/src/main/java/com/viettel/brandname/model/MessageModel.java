package com.viettel.brandname.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MessageModel implements Serializable {

    @SerializedName("groups")
    private String groups;

    @SerializedName("id")
    private String id;

    @SerializedName("time")
    private String time;

    @SerializedName("date")
    private String date;

    @SerializedName("content")
    private String content;

    @SerializedName("brand_name")
    private String brandName;

    @SerializedName("sent_time")
    private String sentTime;

    @SerializedName("status")
    private int status;

    @SerializedName("total_sub")
    private int totalSub;

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public int getTotalSub() {
        return totalSub;
    }

    public void setTotalSub(int totalSub) {
        this.totalSub = totalSub;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}