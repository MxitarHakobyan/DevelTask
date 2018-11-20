package com.example.mxo.develtask.response_data;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class FullNameModel {

    @Expose
    private String title;
    @Expose
    private String first;
    @Expose
    private String last;

    public FullNameModel() {
    }

    public FullNameModel(String title, String first, String last) {
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFullName() {
        return getTitle().toUpperCase() + " " + getFirst() + " " + getLast();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullNameModel model = (FullNameModel) o;
        return Objects.equals(title, model.title) &&
                Objects.equals(first, model.first) &&
                Objects.equals(last, model.last);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int hashCode() {
        return Objects.hash(title, first, last);
    }
}
