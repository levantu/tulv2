package com.viettel.brandname.model;


public class SuggestInputModel {
    private int type;
    private String name;
    private String description;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String brandName) {
        this.name = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SuggestInputModel() {
    }

    public SuggestInputModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        boolean sameSame = false;

        if (object != null && object instanceof SuggestInputModel) {
            if (this.name.equals(((SuggestInputModel) object).getName())) {
                sameSame = true;
            }

        }
        return sameSame;
    }
}