package com.ericharlow.hooplahome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ericharlow.hooplahome.model.HooplaMedia;
import com.ericharlow.hooplahome.model.MediaCollection;
import com.ericharlow.hooplahome.model.MediaType;
import com.ericharlow.hooplahome.retrofit.HooplaHomeClient;
import com.ericharlow.hooplahome.retrofit.ServiceProvider;

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
        return new Callback<MediaCollection>() {
            @Override
            public void onResponse(Call<MediaCollection> call, Response<MediaCollection> response) {
                if (response.isSuccessful()) {
                    MediaCollection collection = response.body();
                    populateMediaTypeViews(collection.collections);
//                    textView.setText(collection.collections.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Response Error", Toast.LENGTH_SHORT).show();
                    Log.d("MainActivity", "response error");
                }
            }

            @Override
            public void onFailure(Call<MediaCollection> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Callback Failure",Toast.LENGTH_LONG).show();
                Log.d("MainActivity", "Callback Failure");
            }
        };
    }

    private void populateMediaTypeViews(List<HooplaMedia> collections) {
        for (HooplaMedia media : collections) {
            int viewId = map(media.kind.id);
            TextView type = findView(viewId);
            type.setText(media.kind.name);
        }
    }

    private int map(int id) {
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

    private void setupUIReferences() {
        textView = findView(R.id.MusicMediaType);
    }

    private <T> T findView(int id) {
        return (T) findViewById(id);
    }
}
