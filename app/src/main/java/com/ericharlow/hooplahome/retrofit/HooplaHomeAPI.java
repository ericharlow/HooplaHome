package com.ericharlow.hooplahome.retrofit;

import com.ericharlow.hooplahome.model.MediaCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by ericharlow on 4/14/16.
 */
public interface HooplaHomeAPI {

    @GET("/home")
    @Headers("ws-api: 2.1")
    Call<MediaCollection> getMediaCollection();
}
