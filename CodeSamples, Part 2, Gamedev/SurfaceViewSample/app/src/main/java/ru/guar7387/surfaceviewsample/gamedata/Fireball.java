package ru.guar7387.surfaceviewsample.gamedata;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import ru.guar7387.surfaceviewsample.R;

public class Fireball implements GameObject {

    private Area area;

    public Fireball(int left, int top) {
        this.area = new ObjectArea(left, top, left + 39, top - 45);
    }

    @Override
    public int getBitmapId() {
        return R.mipmap.bomb;
    }

    @Override
    public Area getArea() {
        return area;
    }

    @Override
    public void changeArea(int left, int top, int right, int bottom) {
        area = new ObjectArea(left, top, right, bottom);
    }

    @Override
    public void render(Bitmap bitmap, Paint paint, Canvas canvas) {
        Area area = getArea();
        if (Math.abs(bitmap.getHeight() - (area.getTop() - area.getBottom())) > 5 ||
                Math.abs(bitmap.getHeight() - (area.getTop() - area.getBottom())) > 5) {
            throw new IllegalArgumentException("Bitmap is not scaled properly");
        }
        canvas.drawBitmap(bitmap, area.getLeft(), area.getTop(), paint);
    }

    public void move() {
        //TODO
    }
}
