package com.github.sakizciadam.snake.api;

import com.github.sakizciadam.snake.api.data.EnumStage;

import java.awt.*;

public class API {


    private String reason;

    protected API(String reason){
        this.reason=reason;
    }

    public void onTick(final int tick){}

    public void onGameRunning(){}

    public void onRender(EnumStage enumStage,Graphics graphics){ }

    public void onDeath(){ }

    public void onMove(EnumStage enumStage,final int[] x,final  int[] y){ }

    public String getReason(){
        return reason;
    }



}
