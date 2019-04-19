package com.example.a3634assignment.Quizzes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a3634assignment.HomePage;
import com.example.a3634assignment.ProfilePage;
import com.example.a3634assignment.R;

public class ResultActivity extends AppCompatActivity {

    TextView score, incorrect;
    Button backMain, goProfile;
    ImageView badgeDisplay;
    int passedTopicNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = findViewById(R.id.result_score);
        incorrect = findViewById(R.id.result_incorrect);
        backMain = findViewById(R.id.result_back_main);
        goProfile = findViewById(R.id.result_go_profile);
        badgeDisplay = findViewById(R.id.course_badge);

        //Displaying user's achievement of the Quiz
        passedTopicNo = getIntent().getExtras().getInt("passedTopic");
        switch (passedTopicNo) {
            case 1:
                score.setText("Score: " + getIntent().getStringExtra("score"));
                incorrect.setText("Incorrect: " + getIntent().getStringExtra("incorrect"));
                new ResultBadgeManager(badgeDisplay).execute("https://www.relybank.com/contentAsset/raw-data/c26ad586-d0b1-4665-9863-4521d9446639/fileAsset");
                break;
            case 2:
                score.setText("Score: " + getIntent().getStringExtra("score"));
                incorrect.setText("Incorrect: " + getIntent().getStringExtra("incorrect"));
                new ResultBadgeManager(badgeDisplay).execute("https://www.westpac.com.au/content/dam/public/wbc/images/business/other/wbc-fh_b_business-award-badge_144x144.png");
                break;
            case 3:
                score.setText("Score: " + getIntent().getStringExtra("score"));
                incorrect.setText("Incorrect: " + getIntent().getStringExtra("incorrect"));
                new ResultBadgeManager(badgeDisplay).execute("https://www.alec.org/app/uploads/2015/11/Tax-Fiscal-Center.png");
                break;
        }

        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, HomePage.class);
                startActivity(intent);
            }
        });

        goProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ProfilePage.class);
                startActivity(intent);
            }
        });
    }
}
