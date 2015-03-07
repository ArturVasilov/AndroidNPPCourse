package ru.guar7387.mediaplayer;

import java.io.IOException;

public interface MusicPlayer {

    public void load(int resourceId) throws IOException;

    public void play();

    public void pause();

    public void stop();

}
