package com.github.sakizciadam.snake;

import java.awt.*;

public abstract class API {
    private API instance;
    private SnakeGame game;

    protected API(SnakeGame game){
        this.game=game;
        instance=this;
    }

    public abstract void onRender(final int stage);

    public abstract void onPaint(final Graphics g);

    public abstract void onMove(final int stage,final int[] x,final  int[] y);

    protected API getAPI(){
        return instance;
    }


}
