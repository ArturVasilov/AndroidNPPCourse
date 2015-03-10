package ru.guar7387.surfaceviewsample.controller;

import ru.guar7387.surfaceviewsample.gamedata.Fireball;
import ru.guar7387.surfaceviewsample.gamedata.Monster;
import ru.guar7387.surfaceviewsample.model.GameModel;

public class GameControllerImplementation implements GameController {

    private final GameModel gameModel;

    public GameControllerImplementation(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public GameModel getModel() {
        return gameModel;
    }

    @Override
    public void start() {
        gameModel.init();
    }

    @Override
    public void update(long timeDifference) {
        for (Fireball fireball : gameModel.getFireballs()) {
            fireball.move();
        }
        for (Monster monster : gameModel.getMonsters()) {
            monster.move();
        }

        //check for screen leave and for intersects
    }

    @Override
    public void shoot(int destinationX, int destinationY) {

    }
}
