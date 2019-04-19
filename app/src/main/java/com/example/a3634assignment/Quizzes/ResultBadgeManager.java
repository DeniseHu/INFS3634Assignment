package com.example.a3634assignment.Quizzes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

//Code reference for ResultBadgeManager: https://www.youtube.com/watch?v=REXgSvEfHq4
public class ResultBadgeManager extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;

    //sets badge in the ResultActivity class
    public ResultBadgeManager(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {

            e.printStackTrace();

        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
