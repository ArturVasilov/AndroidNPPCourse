package ru.guar7387.surfaceviewsample.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.guar7387.surfaceviewsample.gamedata.BossMonster;
import ru.guar7387.surfaceviewsample.gamedata.Fireball;
import ru.guar7387.surfaceviewsample.gamedata.ImagesSize;
import ru.guar7387.surfaceviewsample.gamedata.MiddleMonster;
import ru.guar7387.surfaceviewsample.gamedata.Monster;
import ru.guar7387.surfaceviewsample.gamedata.SmallMonster;
import ru.guar7387.surfaceviewsample.model.GameModel;

public class GameControllerImplementation implements GameController {

    private static final String TAG = GameControllerImplementation.class.getName();

    private final GameModel gameModel;

    private long monsterLastTimeAdded;

    private static final long MONSTERS_MINIMUM_DELAY = 500;

    private final Random random;

    public GameControllerImplementation(GameModel gameModel) {
        this.gameModel = gameModel;
        random = new SecureRandom();
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
        List<Fireball> fireballs = new ArrayList<>(gameModel.getFireballs());
        for (Fireball fireball : fireballs) {
            fireball.move(timeDifference);
        }

        addMonster();
        for (Monster monster : gameModel.getMonsters()) {
            monster.move(timeDifference);
        }

        //check for screen leave and for intersects
    }

    private void addMonster() {
        long time = System.currentTimeMillis();
        if (time - monsterLastTimeAdded < MONSTERS_MINIMUM_DELAY) {
            return;
        }
        //to add a little randomness for monsters time appearence
        boolean shouldAddNewMonster = random.nextInt(10) == 9;
        if (shouldAddNewMonster) {
            Monster monster;
            int rand = random.nextInt(15) + 1;
            int x = ImagesSize.getScreenWidth();
            if (rand == 15) {
                monster = createBossMonster(x);
            }
            else if (rand % 3 == 0) {
                monster = createMiddleMonster(x);
            }
            else {
                monster = createSmallMonster(x);
            }
            getModel().getMonsters().add(monster);
            monsterLastTimeAdded = System.currentTimeMillis();
        }
    }

    private Monster createBossMonster(int x) {
        int y = random.nextInt(ImagesSize.getScreenHeight() - ImagesSize.BossMonster.BITMAP_HEIGHT);
        return new BossMonster(x, y);
    }

    private Monster createMiddleMonster(int x) {
        int y = random.nextInt(ImagesSize.getScreenHeight() - ImagesSize.MiddleMonster.BITMAP_HEIGHT);
        return new MiddleMonster(x, y);
    }

    private Monster createSmallMonster(int x) {
        int y = random.nextInt(ImagesSize.getScreenHeight() - ImagesSize.SmallMonster.BITMAP_HEIGHT);
        return new SmallMonster(x, y);
    }

    @Override
    public void shoot(int destinationX, int destinationY) {
        int x = ImagesSize.Hero.BITMAP_WIDTH;
        int y = (ImagesSize.getScreenHeight() - ImagesSize.Fireball.BITMAP_HEIGHT) / 2;
        if (Math.abs(x - destinationX) < 30 || Math.abs(y - destinationY) < 30) {
            return;
        }
        gameModel.getFireballs().add(new Fireball(x, y, destinationX, destinationY));
    }
}
