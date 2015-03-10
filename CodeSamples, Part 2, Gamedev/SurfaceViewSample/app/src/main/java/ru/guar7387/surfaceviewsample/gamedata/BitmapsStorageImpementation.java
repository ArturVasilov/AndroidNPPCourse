package ru.guar7387.surfaceviewsample.gamedata;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BitmapsStorageImpementation implements BitmapsStorage {

    private final Map<Integer, Bitmap> bitmapMap;

    public BitmapsStorageImpementation() {
        this.bitmapMap = new ConcurrentHashMap<>();
    }

    @Override
    public Bitmap loadBitmap(Resources resources, int id, Area area) {
        Bitmap bitmap = bitmapMap.get(id);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = BitmapFactory.decodeResource(resources, id);

        int height = area.getTop() - area.getBottom();
        int width = area.getRight() - area.getLeft();
        if (Math.abs(bitmap.getHeight() - height) > 5 || Math.abs(bitmap.getHeight() - width) > 5) {
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        }

        bitmapMap.put(id, bitmap);
        return bitmap;
    }
}
