package com.example.a3634assignment.Quizzes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

//Reference code for DialogMessage: https://stackoverflow.com/questions/5070618/how-to-start-an-activity-from-a-dialog-in-android
public class DialogMessage extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Oops!")
                .setMessage("You did not pass 50% of the quiz, please try again.")
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(getContext(), QuizPage.class);
                        getContext().startActivity(intent);

                    }
                });

        return builder.create();
    }
}
