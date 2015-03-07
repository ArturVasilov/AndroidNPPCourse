package ru.guar7387.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class DrawingView extends View {

    private Paint paint;

    private Bitmap bitmap;

    private int bitmapSize;

    public DrawingView(Context context) {
        super(context);
        init();
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
    }

    private void init(Context context, AttributeSet attrs) {
        init();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DrawingView, 0, 0);
        try {
            bitmapSize = array.getInt(R.styleable.DrawingView_bitmap_size, 1);
        }
        finally {
            array.recycle();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 100, 160, 222);
        //drawSunAndSea(canvas);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            int width = bitmap.getWidth() * bitmapSize;
            int height = bitmap.getHeight() * bitmapSize;
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        }
        canvas.drawBitmap(bitmap, 100, 100, paint);
    }

    private void drawSunAndSea(Canvas canvas) {
        final int width = getWidth();
        final int height = getHeight();
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, width, height / 2, paint);
        paint.setColor(Color.GREEN);
        canvas.drawRect(0, height / 2, width, height, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(width / 5, height / 6, (width + height) / 12, paint);
    }
}
