package ru.guar7387.mediaplayer;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MyMusicPlayer implements MusicPlayer {

    private MediaPlayer mediaPlayer;

    private Activity activity;

    public MyMusicPlayer(Activity activity) {
        this.activity = activity;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void load(int resourceId) throws IOException {
        mediaPlayer.setDataSource(activity.getApplicationContext(),
                Uri.parse(
                        "android.resource://ru.guar7387.mediaplayer/"
                        + resourceId));
        mediaPlayer.prepare();
    }

    @Override
    public void play() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void stop() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }
}
