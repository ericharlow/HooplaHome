package com.ericharlow.hooplahome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ericharlow.hooplahome.model.HooplaMedia;
import com.ericharlow.hooplahome.model.MediaCollection;
import com.ericharlow.hooplahome.model.Title;
import com.ericharlow.hooplahome.retrofit.HooplaHomeClient;
import com.ericharlow.hooplahome.retrofit.ServiceProvider;
import com.ericharlow.hooplahome.retrofit.SuccessResponseCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIReferences();
        setupMediaCollectionsRequest();
    }

    private void setupMediaCollectionsRequest() {
        HooplaHomeClient client = ServiceProvider.createService(HooplaHomeClient.class);
        Call<MediaCollection> call = client.getMediaCollection();
        call.enqueue(createMediaCollectionCallback());
    }

    private Callback<MediaCollection> createMediaCollectionCallback() {
        return new SuccessResponseCallback<MediaCollection>() {
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
            populateImageView(media);
        }
    }

    private void populateImageView(HooplaMedia media) {
        int viewId = mapLayoutView(media.kind.id);
        LinearLayout layout = findView(viewId);

        for (Title title : media.titles) {
            String internetUrl = "http://d2snwnmzyr8jue.cloudfront.net/" + title.artKey + "_270.jpeg";
            ImageView targetImageView = new ImageView(MainActivity.this);
            targetImageView.setMaxWidth(400);
            targetImageView.setMaxHeight(400);
            targetImageView.setMinimumHeight(400);
            targetImageView.setMinimumWidth(400);
            layout.addView(targetImageView);

            Glide
                    .with(MainActivity.this)
                    .load(internetUrl)
                    .override(400,400)
                    .error(R.drawable.errorplaceholder)
                    .centerCrop()
                    .into(targetImageView);
        }

    }

    private void populateMediaKindView(HooplaMedia media) {
        int viewId = mapKindView(media.kind.id);
        TextView type = findView(viewId);
        type.setText(media.kind.name);
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

    private int mapLayoutView(int id) {
        switch (id) {
            case 6:
                return R.id.TempMusicLayout;
            case 8:
                return R.id.TempAudioBooksLayout;
            case 7:
                return R.id.TempMoviesLayout;
            case 9:
                return R.id.TempTelevisionLayout;
            case 10:
                return R.id.TempComicLayout;
            case 5:
                return R.id.TempEbookLayout;
            default:
                return R.id.TempEbookLayout;
        }
    }

    private void setupUIReferences() {
        textView = findView(R.id.MusicMediaType);
    }

    private <T> T findView(int id) {
        return (T) findViewById(id);
    }
}
