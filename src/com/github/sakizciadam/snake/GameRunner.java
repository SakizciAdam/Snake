package com.github.sakizciadam.snake;

import com.github.sakizciadam.snake.api.API;
import com.github.sakizciadam.snake.api.data.EnumStage;
import com.github.sakizciadam.snake.utils.FPSCounter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameRunner implements ActionListener {
    public Timer timer;
    private ScreenPanel panel;
    private FPSCounter fpsCounter;
    private int tick;

    public GameRunner(){
        tick=0;
        panel=ScreenPanel.getInstance();
        timer = new Timer(0,this);
        timer.start();
        fpsCounter=new FPSCounter();
        fpsCounter.start();
    }

    protected void frame(){

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
        final boolean debug = SnakeGame.getInstance().isGameRunningOnDebugMode();
        if (panel.isGameRunning()) {
            panel.findAppleIcon();
            panel.findCollision();
            for(API api : SnakeGame.getInstance().getApiManager().getAPI()){
                api.onGameRunning();
            }

        }
        panel.repaint();
        tick++;
        for(API api : SnakeGame.getInstance().getApiManager().getAPI()){
            api.onTick(tick);
        }
    }


}
