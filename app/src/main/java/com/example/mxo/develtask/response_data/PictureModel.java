package com.example.mxo.develtask.response_data;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PictureModel {

    @SerializedName("large")
    private String largePictureUrl;
    @SerializedName("medium")
    private String mediumPictureUrl;
    @SerializedName("thumbnail")
    private String thumbnailPictureUrl;

    public PictureModel() {
    }

    public PictureModel(String largePictureUrl, String mediumPictureUrl, String thumbnailPictureUrl) {
        this.largePictureUrl = largePictureUrl;
        this.mediumPictureUrl = mediumPictureUrl;
        this.thumbnailPictureUrl = thumbnailPictureUrl;
    }

    public String getLargePictureUrl() {
        return largePictureUrl;
    }

    public void setLargePictureUrl(String largePictureUrl) {
        this.largePictureUrl = largePictureUrl;
    }

    public String getMediumPictureUrl() {
        return mediumPictureUrl;
    }

    public void setMediumPictureUrl(String mediumPictureUrl) {
        this.mediumPictureUrl = mediumPictureUrl;
    }

    public String getThumbnailPictureUrl() {
        return thumbnailPictureUrl;
    }

    public void setThumbnailPictureUrl(String thumbnailPictureUrl) {
        this.thumbnailPictureUrl = thumbnailPictureUrl;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PictureModel model = (PictureModel) o;
        return Objects.equals(largePictureUrl, model.largePictureUrl) &&
                Objects.equals(mediumPictureUrl, model.mediumPictureUrl) &&
                Objects.equals(thumbnailPictureUrl, model.thumbnailPictureUrl);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int hashCode() {
        return Objects.hash(largePictureUrl, mediumPictureUrl, thumbnailPictureUrl);
    }
}
