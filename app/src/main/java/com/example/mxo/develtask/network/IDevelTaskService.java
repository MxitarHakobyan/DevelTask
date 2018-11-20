package com.example.mxo.develtask.network;

import com.example.mxo.develtask.response_data.ParentModel;
import com.example.mxo.develtask.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IDevelTaskService {

    @GET(Constants.BASE_URL)
    Call<ParentModel> getParent();

}
