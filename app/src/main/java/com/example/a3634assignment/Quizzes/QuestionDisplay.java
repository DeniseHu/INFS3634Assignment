package com.example.a3634assignment.Quizzes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3634assignment.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Reference code for QuestionDisplay: https://www.youtube.com/watch?v=EzGz6W_AGNk
public class QuestionDisplay extends AppCompatActivity {

    int pressedButtonNo;
    int total = 0;
    int wrong = 0;
    int score = 0;

    private TextView quizQuestion, quizScore, questionCount;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private RadioButton rb;
    private Button btnNext;

    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    DatabaseReference current_user_sdb;
    DatabaseReference current_user_ldb;
    DatabaseReference current_user_fdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        quizQuestion = findViewById(R.id.saving_question);
        quizScore = findViewById(R.id.score_count);
        questionCount = findViewById(R.id.question_count);
        radioGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_btn1);
        rb2 = findViewById(R.id.radio_btn2);
        rb3 = findViewById(R.id.radio_btn3);
        btnNext = findViewById(R.id.quiz_next_btn);

        //Creates "Users" in Firebase with current user id as key to store quiz values
        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        current_user_sdb = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("savingsQuiz");
        current_user_ldb = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("labourQuiz");
        current_user_fdb = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("fiscalQuiz");

        //directs user to the corresponding quiz from the QuizPage class
        pressedButtonNo = getIntent().getExtras().getInt("buttonNo");
        switch (pressedButtonNo) {
            case 1:
                updateSavingQuestion();
                break;

            case 2:
                updateLabourQuestion();
                break;

            case 3:
                updateFiscalQuestion();
                break;
        }
    }

    private void updateSavingQuestion() {

        total++;
        //if user has completed the quiz and gets 50% of the quiz right
        if (total > 5) {
            if (score > 2) {
                //Firebase "Users" value is being updated
                //user will be directed to the ResultActivity class
                current_user_sdb.setValue("Savings Quiz Completed");

                Intent intent = new Intent(QuestionDisplay.this, ResultActivity.class);
                intent.putExtra("passedTopic", 1);
                intent.putExtra("score", String.valueOf(score));
                intent.putExtra("incorrect", String.valueOf(wrong));
                startActivity(intent);

            } else {

                //if user did not pass 50% of quiz, a dialogMessage is displayed
                DialogMessage dialogMessage = new DialogMessage();
                dialogMessage.show(getSupportFragmentManager(), "Dialog Message");
            }

        } else {
            //if user has not yet completed all of the questions, next question will be displayed
            databaseReference = FirebaseDatabase.getInstance().getReference().child("SavingsQs").child(String.valueOf(total));
            mcq();
        }
    }

    private void updateLabourQuestion() {
        total++;
        if (total > 5) {
            if (score > 2) {

                current_user_ldb.setValue("Labour Quiz Completed");

                Intent intent = new Intent(QuestionDisplay.this, ResultActivity.class);
                intent.putExtra("passedTopic", 2);
                intent.putExtra("score", String.valueOf(score));
                intent.putExtra("incorrect", String.valueOf(wrong));
                startActivity(intent);

            } else {

                DialogMessage dialogMessage = new DialogMessage();
                dialogMessage.show(getSupportFragmentManager(), "Dialog Message");
            }

        } else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("LabourQs").child(String.valueOf(total));
            mcq();
        }
    }

    private void updateFiscalQuestion() {
        total++;
        if (total > 5) {

            if (score > 2) {

                Intent intent = new Intent(QuestionDisplay.this, ResultActivity.class);
                intent.putExtra("passedTopic", 3);
                intent.putExtra("score", String.valueOf(score));
                intent.putExtra("incorrect", String.valueOf(wrong));
                startActivity(intent);

                current_user_fdb.setValue("Fiscal Quiz Completed");

            } else {

                DialogMessage dialogMessage = new DialogMessage();
                dialogMessage.show(getSupportFragmentManager(), "Dialog Message");
            }

        } else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("FiscalQs").child(String.valueOf(total));
            mcq();
        }
    }

    public void mcq() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Questions questions = dataSnapshot.getValue(Questions.class);
                quizQuestion.setText(questions.getQuestion());
                rb1.setText(questions.getOption1());
                rb2.setText(questions.getOption2());
                rb3.setText(questions.getOption3());
                questionCount.setText("Question: " + total + "/5");

                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //checks if user has selected a radio button
                        if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                            rbClick(v);
                            //if user answer is right
                            if (rb.getText().equals(questions.getAnswer())) {
                                score++;
                                quizScore.setText("Score: " + score);
                            } else {
                                //if user answer is wrong
                                wrong++;
                                quizScore.setText("Score: " + score);
                            }

                            radioGroup.clearCheck();

                            switch (pressedButtonNo) {
                                case 1:
                                    updateSavingQuestion();
                                    break;

                                case 2:
                                    updateLabourQuestion();
                                    break;

                                case 3:
                                    updateFiscalQuestion();
                                    break;
                            }

                        } else {
                            //if user did not select an option, a toast message will be displayed
                            Toast.makeText(QuestionDisplay.this, "Please select your answer", Toast.LENGTH_LONG).show();
                        }

                    }

                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void rbClick(View v) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        rb = findViewById(radioButtonId);
    }
}
