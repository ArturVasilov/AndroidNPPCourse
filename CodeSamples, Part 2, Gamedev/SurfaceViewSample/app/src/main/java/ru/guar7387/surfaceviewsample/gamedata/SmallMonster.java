package ru.guar7387.surfaceviewsample.gamedata;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import ru.guar7387.surfaceviewsample.R;

public class SmallMonster implements Monster {

    private Area area;

    public SmallMonster(Area area) {
        this.area = area;
    }

    @Override
    public int getBitmapId() {
        return R.mipmap.small_monster;
    }

    @Override
    public int getHitPoints() {
        return 1;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public void move() {

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
}
