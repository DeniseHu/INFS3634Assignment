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

public class SignUpPage extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private Button newAccountBtn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        newAccountBtn = findViewById(R.id.new_account_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        newAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String uEmail = userEmail.getText().toString();
                final String uPassword = userPassword.getText().toString();

                if (uEmail.isEmpty() || uPassword.isEmpty()){

                    errorMessage("Please Verify All Fields");

                } else{

                    CreateUserAccount (uEmail, uPassword);

                }
            }
        });
    }

    //method to create user account with user entered Email and Password
    private void CreateUserAccount(String uEmail, String uPassword) {

        firebaseAuth.createUserWithEmailAndPassword(uEmail, uPassword)
                .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            //user account created successfully
                            Toast.makeText(SignUpPage.this,"Registration Successful", Toast.LENGTH_LONG).show();
                            userEmail.setText("");
                            userPassword.setText("");

                            startActivity(new Intent(SignUpPage.this,HomePage.class));

                        }else{

                            //if user data failed to be stored in FireBase, error message from FireBase is displayed to user
                            Toast.makeText(SignUpPage.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }


    //method to show error message to the user
    private void errorMessage (String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
