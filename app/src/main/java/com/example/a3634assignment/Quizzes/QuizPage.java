package com.example.a3634assignment.Quizzes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a3634assignment.HomePage;
import com.example.a3634assignment.R;

public class QuizPage extends AppCompatActivity {

    Button savingQuiz;
    Button labourQuiz;
    Button fiscalQuiz;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        savingQuiz = findViewById(R.id.saving_test_btn);
        labourQuiz = findViewById(R.id.labour_test_btn);
        fiscalQuiz = findViewById(R.id.fiscal_test_btn);
        backBtn = findViewById(R.id.quiz_back_btn);

        savingQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizPage.this, QuestionDisplay.class);
                intent.putExtra("buttonNo", 1);
                startActivity(intent);
            }
        });

        labourQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizPage.this, QuestionDisplay.class);
                intent.putExtra("buttonNo", 2);
                startActivity(intent);
            }
        });

        fiscalQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizPage.this, QuestionDisplay.class);
                intent.putExtra("buttonNo", 3);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizPage.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}
