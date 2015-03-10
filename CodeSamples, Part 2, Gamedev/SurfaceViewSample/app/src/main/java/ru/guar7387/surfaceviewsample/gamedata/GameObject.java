package ru.guar7387.surfaceviewsample.gamedata;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public interface GameObject {

    public Area getArea();

    public void changeArea(int left, int top, int right, int bottom);

    public void render(Bitmap bitmap, Paint paint, Canvas canvas);

    public int getBitmapId();
}
