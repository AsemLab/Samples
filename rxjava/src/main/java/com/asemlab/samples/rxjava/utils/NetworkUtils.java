package com.asemlab.samples.rxjava.utils;

import com.asemlab.samples.rxjava.remote.ApiResponseCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkUtils {

    public static <T> void enqueueWithCallback(Call<T> call, ApiResponseCallback<T> apiCallback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiCallback.onSuccess(response.body());
                } else {
                    apiCallback.onError(response.code(), response.message());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                apiCallback.onFailure(t);
            }
        });
    }
}