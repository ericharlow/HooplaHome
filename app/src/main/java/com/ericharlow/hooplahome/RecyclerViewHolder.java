package com.ericharlow.hooplahome;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ericharlow on 4/15/16.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageview;

    public RecyclerViewHolder(View view) {
        super(view);
        this.imageview = (ImageView) view;
    }

}
