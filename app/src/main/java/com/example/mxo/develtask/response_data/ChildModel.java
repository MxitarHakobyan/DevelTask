package com.example.mxo.develtask.response_data;

import com.google.gson.annotations.Expose;

public class ChildModel {

    @Expose
    private PictureModel picture;
    @Expose
    private FullNameModel name;
    @Expose
    private LocationModel location;
    @Expose
    private String phone;


    public ChildModel() {
    }

    public ChildModel(PictureModel picture, FullNameModel name, LocationModel location, String phone) {
        this.picture = picture;
        this.name = name;
        this.location = location;
        this.phone = phone;
    }

    public PictureModel getPicture() {
        return picture;
    }

    public void setPicture (PictureModel picture) {
        this.picture = picture;
    }

    public FullNameModel getName() {
        return name;
    }

    public void setName(FullNameModel name) {
        this.name = name;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
