package ru.guar7387.surfaceviewsample.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.guar7387.surfaceviewsample.gamedata.BitmapsStorage;
import ru.guar7387.surfaceviewsample.gamedata.BitmapsStorageImpementation;
import ru.guar7387.surfaceviewsample.gamedata.Fireball;
import ru.guar7387.surfaceviewsample.gamedata.Hero;
import ru.guar7387.surfaceviewsample.gamedata.Monster;

public class StandardGameModel implements GameModel {

    private final int screenWidth;
    private final int screenHeight;

    private final BitmapsStorage bitmapsStorage;

    private final Hero hero;

    private final List<Monster> monsters;

    private final List<Fireball> fireballs;

    public StandardGameModel(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.bitmapsStorage = new BitmapsStorageImpementation();

        hero = new Hero(0, screenHeight / 2);
        monsters = Collections.synchronizedList(new ArrayList<Monster>());
        fireballs = Collections.synchronizedList(new ArrayList<Fireball>());
    }

    @Override
    public void init() {
    }

    @Override
    public Hero getHero() {
        return hero;
    }

    @Override
    public List<Monster> getMonsters() {
        return monsters;
    }

    @Override
    public List<Fireball> getFireballs() {
        return fireballs;
    }

    @Override
    public BitmapsStorage getBitmapsStorage() {
        return bitmapsStorage;
    }

    @Override
    public int getScreenWidth() {
        return screenWidth;
    }

    @Override
    public int getScreenHeight() {
        return screenHeight;
    }
}
