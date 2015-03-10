package ru.guar7387.surfaceviewsample.gamedata;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import ru.guar7387.surfaceviewsample.R;

public class Hero implements GameObject {

    private Area area;

    public Hero(int left, int top) {
        this.area = new ObjectArea(left, top, left + 200, top - 266);
    }

    @Override
    public int getBitmapId() {
        return R.mipmap.hero;
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

    public int getDamage() {
        return 1;
    }
}
