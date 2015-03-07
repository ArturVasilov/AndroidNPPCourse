package ru.guar7387.animationsandgestures;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.logging.Logger;


public class MainActivity extends Activity implements GestureDetector.OnGestureListener{

    private static final String TAG = "MainActivity";

    private GestureDetector gestureDetector;

    private ImageView mImage;

    /*private View.OnTouchListener touch = new View.OnTouchListener() {

        public static final String TAG = "OnTouchListener";

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.getId() != mImage.getId()) {
                return false;
            }
            Log.i(TAG, "Action - " + event.getAction());
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    Log.i(TAG, "Action move");
                    makeAlphaAnimation();
                    break;
            }
            return true;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = (ImageView) findViewById(R.id.penguin);
        gestureDetector = new GestureDetector(getApplicationContext(), this);
        mImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        //mImage.setOnTouchListener(touch);
    }

    private void showFrameAnimation() {
        //noinspection ResourceType
        mImage.setBackgroundResource(R.anim.frame_anim);
        AnimationDrawable animation = (AnimationDrawable) mImage.getBackground();
        animation.start();
    }

    private void makeScaleAnimation() {
        Animation scale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        mImage.startAnimation(scale);
    }

    private void makeRotateAnimation() {
        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        mImage.startAnimation(rotate);
    }

    private void makeTranslateAnimation() {
        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
        mImage.startAnimation(translate);
    }

    private void makeAlphaAnimation() {
        Animation alpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        mImage.startAnimation(alpha);
    }

    private void makeAnimationSet() {
        Animation set = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.set);
        mImage.startAnimation(set);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i(TAG, "OnDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i(TAG, "OnShowPress");
        //makeAlphaAnimation();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG, "OnSingleTapUp");
        makeRotateAnimation();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i(TAG, "OnScroll");
        //makeAnimationSet();
        return false; //I will handle all in onFling
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(TAG, "OnLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i(TAG, "OnFling");
        final int SWIPE_MIN_DISTANCE = 120;
        final int SWIPE_MAX_OFF_PATH = 250;
        final int SWIPE_THRESHOLD_VELOCITY = 100;
        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
            makeAlphaAnimation();
        }
        else if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            makeScaleAnimation();
        }
        else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            makeTranslateAnimation();
        }
        return true;
    }
}
