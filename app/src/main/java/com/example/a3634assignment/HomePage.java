package com.example.a3634assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.a3634assignment.Courses.CourseList;
import com.example.a3634assignment.Quizzes.QuizPage;

public class HomePage extends AppCompatActivity {

    ImageButton profileBtn;
    Button learnBtn;
    Button quizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        profileBtn = findViewById(R.id.profileBtn);
        learnBtn = findViewById(R.id.learn_btn);
        quizBtn = findViewById(R.id.quiz_btn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ProfilePage.class);
                startActivity(intent);
            }
        });

        learnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, CourseList.class);
                startActivity(intent);
            }
        });

        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, QuizPage.class);
                startActivity(intent);
            }
        });
    }
}
