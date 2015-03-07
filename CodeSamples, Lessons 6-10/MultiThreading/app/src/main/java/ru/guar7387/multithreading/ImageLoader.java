package ru.guar7387.multithreading;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader
        extends AsyncTask<String, Bitmap, Void> {

    private ImageView imageView;

    public ImageLoader(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        for (String path : params) {
            try {
                URL url = new URL(path);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                publishProgress(bitmap);
            } catch (IOException ignored) {
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Bitmap... values) {
        imageView.setImageBitmap(values[0]);
    }

}
