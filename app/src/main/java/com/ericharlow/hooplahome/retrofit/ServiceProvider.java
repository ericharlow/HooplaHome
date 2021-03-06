package com.ericharlow.hooplahome.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ericharlow on 4/14/16.
 */
public class ServiceProvider {
    public static final String API_BASE_URL = "https://hoopla-ws-dev.hoopladigital.com";

    public static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    public static OkHttpClient httpClient;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        // for debugging
//        httpClient.interceptors().add(new LoggingInterceptor());
        httpClient = httpClientBuilder.build();
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }


}

