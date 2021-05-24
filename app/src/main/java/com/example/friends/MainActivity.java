package com.example.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaBrowserCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import static com.example.friends.HomeActivity.season;
import static com.google.android.exoplayer2.C.SELECTION_FLAG_FORCED;

public class MainActivity extends AppCompatActivity {


    PlayerView playerView;
    ProgressBar progressBar;
    ImageView btFullscreen, subs;
    SimpleExoPlayer simpleExoPlayer;
    boolean flag = true;
    Uri uri, subtitle_uri;
    ExtractorMediaSource mediaSource;
    TrackSelector trackSelector;
    SubtitleView subtitleView;
    int t=0;
    String subtitleUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String url,name, number;
        url = intent.getStringExtra("url");
        name = intent.getStringExtra("title");
        number = intent.getStringExtra("number");
        //if(season<5) {
            subtitleUrl = intent.getStringExtra("subs");
        //}

        playerView = findViewById(R.id.player_view);
        progressBar = findViewById(R.id.progress_bar);
        btFullscreen = playerView.findViewById(R.id.bt_fullscreen);
        subs = playerView.findViewById(R.id.subs);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        uri = Uri.parse(url);
        if(season<5) {
            subtitle_uri = Uri.parse(subtitleUrl);
        }

        LoadControl loadControl = new DefaultLoadControl();

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();


        trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(MainActivity.this, trackSelector, loadControl);

        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource mediaSource = new ExtractorMediaSource(uri,factory,extractorsFactory,null,null);

        playerView.setPlayer(simpleExoPlayer);

        playerView.setKeepScreenOn(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //playWithCaption();
        subtitleView = (SubtitleView)findViewById(com.google.android.exoplayer2.R.id.exo_subtitles);
        simpleExoPlayer.setTextOutput(new ComponentListener());


        subtitleView.setStyle(new CaptionStyleCompat(Color.WHITE,
                Color.TRANSPARENT, Color.TRANSPARENT, CaptionStyleCompat.EDGE_TYPE_NONE, Color.WHITE,
                Typeface.DEFAULT_BOLD));

        if(season<5) {
            Format subtitleFormat = Format.createTextSampleFormat(
                    "English Subtitles",
                    MimeTypes.APPLICATION_SUBRIP,
                    C.SELECTION_FLAG_DEFAULT,
                    "en");

            MediaSource subtitleSource =
                    new SingleSampleMediaSource.Factory(factory)
                            .createMediaSource(subtitle_uri,subtitleFormat, C.TIME_UNSET);



            MergingMediaSource mergedSource =
                    new MergingMediaSource(mediaSource, subtitleSource);

            simpleExoPlayer.prepare(mergedSource);

        }

        else
            simpleExoPlayer.prepare(mediaSource);

        subs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t%2==0){
                    subs.setImageDrawable(getDrawable(R.drawable.ic_subtitles_off));
                    subtitleView.setVisibility(View.GONE);
                }
                else {
                    subs.setImageDrawable(getDrawable(R.drawable.ic_subtitles));
                    subtitleView.setVisibility(View.VISIBLE);
                }
                t++;

            }
        });


        TextView textView = findViewById(R.id.title);
        textView.setText(season +"x"+number+" - "+name);



        ImageButton imageButton = findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == Player.STATE_BUFFERING)
                    progressBar.setVisibility(View.VISIBLE);
                else if (playbackState == Player.STATE_READY)
                    progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });

        btFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    btFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    flag = false;
                }
                else {
                    btFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_exit));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    flag = true;
                }
            }
        });
    }



    @Override
    protected void onPause() {
        super.onPause();
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();
    }


    private void playWithCaption(){
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "exo-demo"));

        MediaSource contentMediaSource = buildMediaSource(uri);
        MediaSource[] mediaSources = new MediaSource[2]; //The Size must change depending on the Uris
        mediaSources[0] = contentMediaSource; // uri

        //Add subtitles
        SingleSampleMediaSource subtitleSource = new SingleSampleMediaSource(subtitle_uri, dataSourceFactory,
                Format.createTextSampleFormat(null, MimeTypes.APPLICATION_SUBRIP, Format.NO_VALUE, "en", null),
                C.TIME_UNSET);

        mediaSources[1] = subtitleSource;

        MediaSource mediaSource = new MergingMediaSource(mediaSources);

        // Prepare the player with the source.
        //player.seekTo(contentPosition);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
    }

    public class ComponentListener implements TextRenderer.Output {
        @Override
        public void onCues(List<Cue> cues) {
            if (subtitleView != null) {
                subtitleView.onCues(cues);
            }
        }
    }

    private MediaSource buildMediaSource(Uri parse) {
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "exo-demo"));

        mediaSource = new ExtractorMediaSource(parse, dataSourceFactory,new DefaultExtractorsFactory(),new Handler(),null);

        return mediaSource;
    }



}