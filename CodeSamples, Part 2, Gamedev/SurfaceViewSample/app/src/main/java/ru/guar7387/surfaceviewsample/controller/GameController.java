package ru.guar7387.surfaceviewsample.controller;

import ru.guar7387.surfaceviewsample.model.GameModel;

public interface GameController {

    public GameModel getModel();

    public void update(long timeDifference);

    public void shoot(int destinationX, int destinationY);

    public void start();
}
