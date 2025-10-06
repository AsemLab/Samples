package com.asemlab.samples.rxjava.remote;

public interface ApiResponseCallback<T> {
    void onSuccess(T response);

    void onError(int responseCode, String message);

    void onFailure(Throwable t);
}