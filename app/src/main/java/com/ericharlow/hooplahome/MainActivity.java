package com.ericharlow.hooplahome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ericharlow.hooplahome.model.DataModel;
import com.ericharlow.hooplahome.model.HooplaMedia;
import com.ericharlow.hooplahome.model.MediaCollection;
import com.ericharlow.hooplahome.model.Title;
import com.ericharlow.hooplahome.retrofit.HooplaHomeAPI;
import com.ericharlow.hooplahome.retrofit.ServiceProvider;
import com.ericharlow.hooplahome.retrofit.SuccessResponseCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialAdapters(new int[]{6,8,9,7,10,5});
        setupMediaCollectionsRequest();
    }

    private void setupMediaCollectionsRequest() {
        HooplaHomeAPI client = ServiceProvider.createService(HooplaHomeAPI.class);
        Call<MediaCollection> call = client.getMediaCollection();
        call.enqueue(createMediaCollectionCallback());
    }

    private Callback<MediaCollection> createMediaCollectionCallback() {
        return new SuccessResponseCallback<MediaCollection>(MainActivity.this) {
            @Override
            public void onSuccessResponse(Call<MediaCollection> call, Response<MediaCollection> response) {
                MediaCollection collection = response.body();
                populateViews(collection.collections);
            }
        };
    }

    private void populateViews(List<HooplaMedia> collections) {
        for (HooplaMedia media : collections) {
            populateMediaKindView(media);
            populateRecyclerView(media);
        }
    }

    private void populateMediaKindView(HooplaMedia media) {
        int viewId = mapKindView(media.kind.id);
        TextView type = findView(viewId);
        type.setText(media.kind.name);
    }

    private void populateRecyclerView(HooplaMedia media) {
        RecyclerView recyclerView = getRecyclerViewById(media.kind.id);

        ArrayList<DataModel> arrayList = new ArrayList<>();
        for (Title title : media.titles) {
            DataModel dataModel = new DataModel("http://d2snwnmzyr8jue.cloudfront.net/"
                    + title.artKey + "_270.jpeg");
            arrayList.add(dataModel);
        }

        RecyclerViewAdapter  adapter = new RecyclerViewAdapter(MainActivity.this, arrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setInitialAdapters(int[] ints) {
        for (int id : ints) {
            initializeRecyclerViewWithDefaultAdapter(id);
        }
    }

    private void initializeRecyclerViewWithDefaultAdapter(int id) {
        RecyclerView recyclerView = getRecyclerViewById(id);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        RecyclerViewAdapter  adapter = new RecyclerViewAdapter(MainActivity.this, new ArrayList<DataModel>(0));
        recyclerView.setAdapter(adapter);
    }

    private RecyclerView getRecyclerViewById(int id) {
        int viewId = mapRecyclerView(id);
        return findView(viewId);
    }

    private int mapRecyclerView(int id) {
        switch (id) {
            case 6:
                return R.id.MusicLayout;
            case 8:
                return R.id.AudioBooksLayout;
            case 7:
                return R.id.MoviesLayout;
            case 9:
                return R.id.TelevisionLayout;
            case 10:
                return R.id.ComicLayout;
            case 5:
                return R.id.EbookLayout;
            default:
                return R.id.EbookLayout;
        }
    }

    private int mapKindView(int id) {
        switch (id) {
            case 6:
                return R.id.MusicMediaType;
            case 8:
                return R.id.AudioBooksMediaType;
            case 7:
                return R.id.MoviesMediaType;
            case 9:
                return R.id.TelevisionMediaType;
            case 10:
                return R.id.ComicMediaType;
            case 5:
                return R.id.EbooksMediaType;
            default:
                return R.id.EbooksMediaType;
        }
    }

    private <T> T findView(int id) {
        return (T) findViewById(id);
    }
}
