package ru.guar7387.surfaceviewsample.gamedata;

public class ObjectArea implements Area {

    private int left;
    private int top;
    private int right;
    private int bottom;

    public ObjectArea(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void changePosition(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public int getLeft() {
        return left;
    }

    @Override
    public int getTop() {
        return top;
    }

    @Override
    public int getRight() {
        return right;
    }

    @Override
    public int getBottom() {
        return bottom;
    }

    @Override
    public boolean intersects(Area area) {
        //TODO
        return false;
    }

    @Override
    public String toString() {
        return "ObjectArea{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                '}';
    }
}
