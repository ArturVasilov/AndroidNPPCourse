package ru.guar7387.mediaplayer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    public static final String RESOURCE_ID = "ID";
    public static final String ACTION_PLAY = "PLAY";

    private MediaPlayer mediaPlayer;

    private final MusicBinder binder = new MusicBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MusicService", "onStartCommand");
        if (intent == null) {
            stopSelf();
            return START_NOT_STICKY;
        }
        if (intent.getAction().equals(ACTION_PLAY)) {
            int id = intent.getIntExtra(RESOURCE_ID, R.raw.music);
            mediaPlayer = MediaPlayer.create(getApplicationContext(), id);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(
                    new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            stopSelf();
                        }
                    }
            );
            showNotification();
        }
        return START_STICKY;
    }

    private void showNotification() {
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),
                0, new Intent(getApplicationContext(), MusicReceiver.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentText("Playing music");
        builder.setContentTitle("Symphony 5");
        builder.setSmallIcon(android.R.drawable.ic_media_play);
        builder.setContentIntent(pendingIntent);

        Notification notification = getNotification(builder);
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        startForeground(1, notification);
    }

    private Notification getNotification(
            Notification.Builder builder) {
        if (Build.VERSION.SDK_INT >= 16) {
            return builder.build();
        }
        //noinspection deprecation
        return builder.getNotification();
    }

    public void play() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void onDestroy() {
        Log.i("MusicService", "onDestroy");
        stopForeground(true);
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

}
