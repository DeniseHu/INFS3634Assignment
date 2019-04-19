package com.example.a3634assignment.Videos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a3634assignment.Courses.CourseList;
import com.example.a3634assignment.Quizzes.QuizPage;
import com.example.a3634assignment.R;

public class VideoPage extends AppCompatActivity {

    private Button btnBackVideo;
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_page);

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
