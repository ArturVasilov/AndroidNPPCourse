package ru.guar7387.surfaceviewsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

import ru.guar7387.surfaceviewsample.controller.GameController;
import ru.guar7387.surfaceviewsample.controller.GameControllerImplementation;
import ru.guar7387.surfaceviewsample.gamedata.BitmapsStorage;
import ru.guar7387.surfaceviewsample.gamedata.Fireball;
import ru.guar7387.surfaceviewsample.gamedata.Hero;
import ru.guar7387.surfaceviewsample.gamedata.Monster;
import ru.guar7387.surfaceviewsample.model.GameModel;
import ru.guar7387.surfaceviewsample.model.StandardGameModel;

public class GameThread extends Thread {

    private static final long MINIMUM_RENDER_DELAY = 18;

    private final Context mContext;

    private final GameView mGameView;

    private final GameModel mGameModel;

    private final GameController mGameController;

    private final Paint mPaint;

    private volatile boolean isRunning = true;

    public GameThread(Context context, GameView gameView) {
        this.mContext = context;
        this.mGameView = gameView;

        this.mGameModel = new StandardGameModel(gameView.getWidth(), gameView.getHeight());
        this.mGameController = new GameControllerImplementation(mGameModel);

        mPaint = new Paint();
    }

    @Override
    public void run() {
        mGameController.start();
        while (isRunning) {
            long startTime = System.currentTimeMillis();
            render();
            long endTime = System.currentTimeMillis();

            long difference = endTime - startTime;
            if (difference < MINIMUM_RENDER_DELAY) {
                try {
                    sleep(MINIMUM_RENDER_DELAY - difference);
                } catch (InterruptedException ie) {
                    break;
                }
                mGameController.update(MINIMUM_RENDER_DELAY);
            }
            else {
                try {
                    sleep(difference);
                } catch (InterruptedException ie) {
                    break;
                }
                mGameController.update(MINIMUM_RENDER_DELAY);
            }
        }
    }

    private void render() {
        SurfaceHolder holder = mGameView.getHolder();
        if (holder == null) {
            return;
        }
        Canvas canvas = holder.lockCanvas();
        if (canvas == null) {
            return;
        }
        canvas.drawARGB(255, 0, 0, 0);

        BitmapsStorage bitmapsStorage = mGameModel.getBitmapsStorage();

        Hero hero = mGameModel.getHero();
        Bitmap bitmap = bitmapsStorage.loadBitmap(mContext.getResources(), hero.getBitmapId(), hero.getObjectArea());
        hero.render(bitmap, mPaint, canvas);
        for (Monster monster : mGameModel.getMonsters()) {
            bitmap = bitmapsStorage.loadBitmap(mContext.getResources(), monster.getBitmapId(), monster.getObjectArea());
            monster.render(bitmap, mPaint, canvas);
        }
        //to prevent concuttrent modification
        List<Fireball> fireballs = new ArrayList<>(mGameModel.getFireballs());
        for (Fireball fireball : fireballs) {
            bitmap = bitmapsStorage.loadBitmap(mContext.getResources(), fireball.getBitmapId(), fireball.getObjectArea());
            fireball.render(bitmap, mPaint, canvas);
        }
        holder.unlockCanvasAndPost(canvas);
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public GameController getController() {
        return mGameController;
    }
}
