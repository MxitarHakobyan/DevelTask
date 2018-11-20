package com.example.mxo.develtask.response_data;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class LocationModel {

    @Expose
    private String state;
    @Expose
    private String city;

    public LocationModel() {
    }

    public LocationModel(String state, String city) {
        this.state = state;
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWhereabouts() {
        return getState() + " " + getCity();
     }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationModel model = (LocationModel) o;
        return Objects.equals(state, model.state) &&
                Objects.equals(city, model.city);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int hashCode() {
        return Objects.hash(state, city);
    }
}
