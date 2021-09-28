package com.video.view;

import com.video.controller.VideoController;

public class PlayVideoTimer implements Runnable{
	private Thread threadVideoPlayer;
    private boolean go,live;
    private int seconds;
    private int videoLength;
    private VideoPlayerWindow videoPlayerWindow;

    public PlayVideoTimer(VideoPlayerWindow videoPlayerWindow, int videoId, int userId) {
        this.videoPlayerWindow = videoPlayerWindow;
        this.videoLength = VideoController.searchVideoById(videoId, userId).getVideoLength();
    }

    public void run() {
        try {
            while (isLive()&&seconds<videoLength) {
                synchronized(this) {
                    while (!isGo())
                        wait();
                }
                Thread.sleep(1000);
                seconds++;
                updateThread();
            }
        } catch (InterruptedException e) {}
    }

    public void createThread() {
        threadVideoPlayer = new Thread(this);
        threadVideoPlayer.start();
    }

    private void updateThread() {
        if (isLive() == true) {
            int hr= seconds/3600;
            int min =(seconds-hr*3600)/60;
            int seg = seconds-hr*3600-min*60;
            videoPlayerWindow.getDisplay().setText(""+hr+" : "+min+" : "+seg);
        } else {
            seconds = 0;
            videoPlayerWindow.getDisplay().setText("0 : 0 : 0");
        }
    }

    public void pauseThread() {
        setGo(false);
    }

    public synchronized void continueThread() {
        setGo(true);
        notify();
    }

   
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isGo() {
        return go;
    }

    public void setGo(boolean go) {
        this.go = go;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
    public Thread getThreadVideoPlayer() {
    	return threadVideoPlayer;
    }
}