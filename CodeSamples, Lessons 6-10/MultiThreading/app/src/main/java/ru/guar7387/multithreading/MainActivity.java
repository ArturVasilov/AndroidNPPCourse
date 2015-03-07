package ru.guar7387.multithreading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {


    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.textView);
        ImageLoader loader = new ImageLoader(imageView);
        loader.execute("http://static.giantbomb.com/uploads/scale_medium/0/3683/1120634-penguin_chick.jpg",
                "http://img2.wikia.nocookie.net/__cb20140217220120/happyfeet/images/9/95/Companions_adelie_penguins.jpg",
                "http://ngm.nationalgeographic.com/2012/11/emperor-penguins/img/02-airborne-penguin-exits-water_1600.jpg",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQUMhOS2ZjBC4lZNXdOGyCl3i3esl9xh8YvU5ggm6yNGXi7NzpE",
                "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRBlaMUxwBEbdcmLWqkNZcXk1H3DDGG3JUm3FV4lyuUJLRK7auklw");
    }

}














/*new ImageLoader(imageView).execute(
        //*/