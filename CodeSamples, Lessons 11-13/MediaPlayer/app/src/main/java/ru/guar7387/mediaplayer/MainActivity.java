package ru.guar7387.mediaplayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends Activity
        implements View.OnClickListener {

    private ImageButton playButton;

    private ImageButton pauseButton;

    private Intent serviceIntent;

    private MusicService musicService;

    private boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (ImageButton) findViewById(R.id.playMusic);
        pauseButton = (ImageButton) findViewById(R.id.pauseMusic);
        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        pauseButton.setEnabled(false);
        initIntent();
        runService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private void runService() {
        startService(serviceIntent);
        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
    }

    private ServiceConnection connection =
            new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name,
                                               IBinder service) {
                    MusicService.MusicBinder binder =
                            (MusicService.MusicBinder) service;
                    musicService = binder.getService();
                    isBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isBound = false;
                }
            };

    private void initIntent() {
        serviceIntent = new Intent(getApplicationContext(), MusicService.class);
        serviceIntent.setAction(MusicService.ACTION_PLAY);
        serviceIntent.putExtra(MusicService.RESOURCE_ID, R.raw.music);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playMusic:
                if (isBound) {
                    musicService.play();
                }
                pauseButton.setEnabled(true);
                playButton.setEnabled(false);
                break;

            case R.id.pauseMusic:
                if (isBound) {
                    musicService.pause();
                }
                pauseButton.setEnabled(false);
                playButton.setEnabled(true);
                break;
        }
    }
}
