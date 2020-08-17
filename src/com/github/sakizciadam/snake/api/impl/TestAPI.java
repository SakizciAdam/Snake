package com.github.sakizciadam.snake.api.impl;

import com.github.sakizciadam.snake.api.API;
import com.github.sakizciadam.snake.api.data.EnumStage;

import java.awt.*;

public class TestAPI extends API {

    public TestAPI(){
        super("So people can skin code from here");
    }


    @Override
    public void onMove(EnumStage enumStage, int[] x, int[] y) {
        //System.out.println("Head Position\nX:"+x[0]+"\tY:"+y[0]);
    }

    @Override
    public void onRender(EnumStage enumStage, final Graphics graphics){
        /*
            graphics.setColor(Color.yellow);
                graphics.fillRect(15,15,8,8);
         */

    }

    @Override
    public void onDeath(){
        //System.out.println("oh no I died...");
    }

    public void onTick(final int tick){
        //System.out.println("Current Tick: "+tick);
    }

    public void onGameRunning(){
        //System.out.println("Snake is still alive!");
    }

}
