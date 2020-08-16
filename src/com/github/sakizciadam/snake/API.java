package com.github.sakizciadam.snake;

import java.awt.*;

public class API {
    private API instance;
    private SnakeGame game;

    protected API(SnakeGame game){
        this.game=game;
        instance=this;
    }

    public void onRender(final int stage){

    }

    public void onPaint(final Graphics g){

    }

    public void onMove(final int stage,final int[] x,final  int[] y){

    }

    protected API getAPI(){
        return instance;
    }


}
