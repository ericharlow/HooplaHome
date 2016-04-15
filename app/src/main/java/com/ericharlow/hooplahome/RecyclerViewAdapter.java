package com.ericharlow.hooplahome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ericharlow.hooplahome.model.DataModel;

import java.util.ArrayList;

/**
 * Created by ericharlow on 4/15/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<DataModel> arrayList;
    private int imageWidth;
    private int imageHeight;

    public RecyclerViewAdapter(Context context, ArrayList<DataModel> arrayList) {
        this.arrayList = arrayList;
        imageHeight = (int) context.getResources().getDimension(R.dimen.image_height);
        imageWidth = (int) context.getResources().getDimension(R.dimen.image_width);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final DataModel model = arrayList.get(position);
        Context context = holder.imageview.getContext();

        Glide
                .with(context)
                .load(model.getUrl())
                .override(imageHeight,imageWidth)
                .error(R.drawable.errorplaceholder)
                .centerCrop()
                .into(holder.imageview);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        View mainGroup = mInflater.inflate(
                R.layout.item_row, viewGroup, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
        return listHolder;
    }

}