package com.example.a3634assignment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {

    TextView userEmail;
    Button logoutBtn;
    FrameLayout savingsLayout;
    FrameLayout labourLayout;
    FrameLayout fiscalLayout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;

    String SAVINGS_BADGE = "Savings Quiz Completed";
    String LABOUR_BADGE = "Labour Quiz Completed";
    String FISCAL_BADGE = "Fiscal Quiz Completed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        userEmail = findViewById(R.id.profileEmail);
        logoutBtn = findViewById(R.id.sign_out_btn);
        savingsLayout = findViewById(R.id.saving_badge_wrap);
        labourLayout = findViewById(R.id.labour_badge_wrap);
        fiscalLayout = findViewById(R.id.fiscal_badge_wrap);

        //Displays user email in Profile Page
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userEmail.setText(firebaseUser.getEmail());

        //Reference code for storing value: https://www.youtube.com/watch?v=vkf5z1raSyE
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference savingsReference = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("savingsQuiz");
        savingsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childValue = String.valueOf(dataSnapshot.getValue());
                //if user has completed the quiz, Badge will light up
                if (childValue.equals(SAVINGS_BADGE)) {
                    savingsLayout.setAlpha(1.0f);
                } else {
                    savingsLayout.setAlpha(0.5f);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference labourReference = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("labourQuiz");
        labourReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childValue = String.valueOf(dataSnapshot.getValue());
                if (childValue.equals(LABOUR_BADGE)) {
                    labourLayout.setAlpha(1.0f);
                } else {
                    labourLayout.setAlpha(0.5f);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference fiscalReference = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid()).child("fiscalQuiz");
        fiscalReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childValue = String.valueOf(dataSnapshot.getValue());
                if (childValue.equals(FISCAL_BADGE)) {
                    fiscalLayout.setAlpha(1.0f);
                } else {
                    fiscalLayout.setAlpha(0.5f);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Set onClickListener for the Log Out button to allow user to exist the App
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfilePage.this, LoginPage.class));
            }
        });
    }
}