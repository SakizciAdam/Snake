package com.github.sakizciadam.snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameRunner implements ActionListener {
    public Timer timer;
    private ScreenPanel panel;
    private FPSCounter fpsCounter;

    public GameRunner(){
        panel=ScreenPanel.getInstance();
        timer = new Timer(0,this);
        timer.start();
        fpsCounter=new FPSCounter();
        fpsCounter.start();
    }

    protected void onPreRender(){

    }

    protected void onPostRender(){
        fpsCounter.frame();

    }

    protected String getFPS(){
        final int fps = fpsCounter.getLast();

        if(fps<0){
            return "Calculating FPS";
        }
        return "FPS "+String.valueOf(fps);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (panel.isGameRunning()) {

            panel.findAppleIcon();
            panel.findCollision();

        }

        panel.repaint();
    }


}
