package com.ericharlow.hooplahome.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.ericharlow.hooplahome.retrofit.ServiceProvider;

import java.io.InputStream;

/**
 * Created by ericharlow on 4/15/16.
 */
public class SimpleGlideModule implements GlideModule {
    @Override public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override public void registerComponents(Context context, Glide glide) {
        // can I remove the integration dependency?
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(ServiceProvider.httpClient));
    }
}
