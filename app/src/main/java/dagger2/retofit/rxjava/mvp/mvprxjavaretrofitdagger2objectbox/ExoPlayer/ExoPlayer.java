package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.ExoPlayer;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.BaseApp;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.Data;
import dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.R;
import io.objectbox.Box;
import io.objectbox.BoxStore;

import static dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.MyApplication.getBoxStore;

public class ExoPlayer extends BaseApp {

    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    @BindView(R.id.tvDisLike)
    TextView tvDisLike;
    @BindView(R.id.tvLike)
    TextView tvLike;
    @BindView(R.id.tvLikeCount)
    TextView tvLikeCount;
    @BindView(R.id.tvDisLikeCount)
    TextView tvDisLikeCount;
    BoxStore boxStore;
    Box<Data> box;
    int countLike = 0, countdisLike = 0;
    Data data;
    long putLike,putDisLike;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        renderView();
    }


    private void renderView() {
        setContentView(R.layout.activity_exo_player);
        ButterKnife.bind(this);
        box = getBoxStore().boxFor(Data.class);
        data = new Data();
        box.put(data);
        showVideo();
    }

    @OnClick(R.id.layLikeCount)
    public void likeCount(View view) {
        countLike++;
        data.setLikeCount(countLike);
        putLike = box.put(data);
        getLikeCount();
    }

    private void getLikeCount() {
        Data data = box.get(putLike);
        tvLikeCount.setText(""+ data.getLikeCount());
    }

    @OnClick(R.id.layDisLikeCount)
    public void disLikCount(View view) {
        countdisLike++;
        data.setDisLikeCount(countdisLike);
        putDisLike = box.put(data);
        getDisLikeCount();
    }

    private void getDisLikeCount() {
        Data data1 = box.get(putDisLike);
        tvDisLikeCount.setText(""+data1.getDisLikeCount());
    }

    private void showVideo() {
        String videoURL = getIntent().getExtras().getString(getResources().getString(R.string.video_url));
        Log.e("video_url", videoURL);
        try {

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            Uri videoURI = Uri.parse(videoURL);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        } catch (Exception e) {
            Log.e("MainAcvtivity", " exoplayer error " + e.toString());
        }

    }


}
