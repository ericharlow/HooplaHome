package com.ericharlow.hooplahome.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ericharlow on 4/15/16.
 */
public abstract class SuccessResponseCallback<T> implements Callback<T> {
    private Context context;

    public SuccessResponseCallback(Context context) {
        this.context = context;
    }

    public abstract void onSuccessResponse(Call<T> call, Response<T> response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
           onSuccessResponse(call, response);
        } else {
            Log.d("MainActivity", "response error");
            try {
                Toast.makeText(context,response.errorBody().string(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d("MainActivity", "Callback Failure");
        Toast.makeText(context,t.getCause().getMessage(), Toast.LENGTH_LONG).show();
    }
}
