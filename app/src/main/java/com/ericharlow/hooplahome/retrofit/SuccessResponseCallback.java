package com.ericharlow.hooplahome.retrofit;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ericharlow on 4/15/16.
 */
public abstract class SuccessResponseCallback<T> implements Callback<T> {
    public abstract void onSuccessResponse(Call<T> call, Response<T> response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
           onSuccessResponse(call, response);
        } else {
            Log.d("MainActivity", "response error");
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d("MainActivity", "Callback Failure");
    }
}
