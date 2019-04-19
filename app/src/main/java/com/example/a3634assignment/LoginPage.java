package com.example.a3634assignment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//Code reference for Login & SignUp: https://www.youtube.com/watch?v=KFULmVXpO-A&t=153s
public class LoginPage extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private Button loginBtn;
    private Button signupBtn;

    //declare instance of FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userEmail = (EditText) findViewById(R.id.email);
        userPassword = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        signupBtn = (Button) findViewById(R.id.signupBtn);

        //initialise Firebase
        mAuth = FirebaseAuth.getInstance();

        //OnClickListener to check user details against the FireBase data
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uEmail = userEmail.getText().toString();
                final String uPassword = userPassword.getText().toString();

                if (uEmail.isEmpty() || uPassword.isEmpty()) {
                    errorMessage("Please Verify All Fields");

                } else {

                    mAuth.signInWithEmailAndPassword(userEmail.getText().toString(),
                            userPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //if user logged in successfully
                            if (task.isSuccessful()) {
                                startActivity(new Intent(LoginPage.this, HomePage.class));

                            } else {
                                //if user failed to login, toast message is displayed
                                Toast.makeText(LoginPage.this, "User not found or Incorrect password", Toast.LENGTH_LONG).show();
                                userEmail.setText("");
                                userPassword.setText("");
                            }
                        }
                    });
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, SignUpPage.class));
            }
        });
    }

    private void errorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}