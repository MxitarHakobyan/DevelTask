package com.example.mxo.develtask.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mxo.develtask.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DevelTaskClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response = chain.proceed(request);

                        if (response.code() == 401 || response.code() == 500) {
                            Log.d("TAGO", "ERROR: response code = " + response.code());
                            return response;
                        }

                        return response;
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}