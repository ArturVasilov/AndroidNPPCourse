package ru.guar7387.surfaceviewsample.gamedata;

public class ImagesSize {

    private static boolean wasInit = false;

    public static void init(int screenWidth, int screenHeight) {
        wasInit = true;
        SCREEN_WIDTH = screenWidth;
        SCREEN_HEIGHT = screenHeight;
    }

    private static int SCREEN_WIDTH;
    private static int SCREEN_HEIGHT;

    public static int getScreenWidth() {
        if (wasInit) {
            return SCREEN_WIDTH;
        }
        throw new IllegalStateException("You should instantiate screen params first");
    }

    public static int getScreenHeight() {
        if (wasInit) {
            return SCREEN_HEIGHT;
        }
        throw new IllegalStateException("You should instantiate screen params first");
    }

    public static interface Hero {
        int BITMAP_WIDTH = 200;
        int BITMAP_HEIGHT = 266;
    }

    public static interface Fireball {
        int BITMAP_WIDTH = 45;
        int BITMAP_HEIGHT = 39;
    }

    public static interface SmallMonster {
        int BITMAP_WIDTH = 76;
        int BITMAP_HEIGHT = 70;
    }

    public static interface MiddleMonster {
        int BITMAP_WIDTH = 140;
        int BITMAP_HEIGHT = 136;
    }

    public static interface BossMonster {
        int BITMAP_WIDTH = 400;
        int BITMAP_HEIGHT = 297;
    }
}
