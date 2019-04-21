package com.example.a3634assignment.Videos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;
import com.example.a3634assignment.Courses.CourseList;
import com.example.a3634assignment.Quizzes.QuizPage;
import com.example.a3634assignment.R;

public class VideoPage extends YouTubeBaseActivity {

    private Button btnBackVideo;
    private Button btnTest;
    private FrameLayout frameLayout;
    private static final String TAG = "VideoPage";
    private Videos mVideos;
    private TextView mVideoName;
    private TextView mContentDes;
    private YouTubePlayerView mYoutubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    //Reference code: https://www.youtube.com/watch?v=W4hTJybfU7s&t=548s

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_page);

        mVideoName = findViewById(R.id.video_name);
        mContentDes = findViewById(R.id.content_des);
        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_play);

        Intent intent = getIntent();
        int position = intent.getIntExtra(CourseList.EXTRA_MESSAGE, 0);
        mVideos = Videos.getVideos().get(position);
        mVideoName.setText(mVideos.getVideoName());

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                                boolean b) {
                Log.d(TAG, "onClick: Done Initializing.");
                youTubePlayer.loadVideo(mVideos.getVideoUrl());
            }


            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult
                    youTubeInitializationResult) {
                Log.d(TAG, "onClick: Fail to initialize.");
            }

        };

        mYoutubePlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Initializing Youtube Player.");
                mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);

            }
        });

        mContentDes.setText(mVideos.getContentDes());

        btnBackVideo = findViewById(R.id.video_back_btn);
        btnTest = findViewById(R.id.test_btn);

        btnBackVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoPage.this, CourseList.class);
                startActivity(intent);
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoPage.this, QuizPage.class);
                startActivity(intent);
            }
        });
    }
}
