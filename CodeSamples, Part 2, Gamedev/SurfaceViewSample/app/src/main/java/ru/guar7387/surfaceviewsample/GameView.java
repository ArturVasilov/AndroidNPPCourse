package ru.guar7387.surfaceviewsample;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ru.guar7387.surfaceviewsample.controller.GameController;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread mGameThread;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mGameThread = new GameThread(getContext(), this);
        mGameThread.setRunning(true);
        mGameThread.start();

        /*Canvas canvas = holder.lockCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(10, 10, 20, paint);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(1800, 10, 20, paint);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(10, 1000, 20, paint);
        paint.setColor(Color.CYAN);
        canvas.drawCircle(1800, 1000, 20, paint);
        holder.unlockCanvasAndPost(canvas);*/
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mGameThread.setRunning(false);
        while (true) {
            try {
                mGameThread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            GameController controller = mGameThread.getController();
            controller.shoot((int) event.getX(), (int) event.getY());
            return true;
        }
        return super.onTouchEvent(event);
    }
}
