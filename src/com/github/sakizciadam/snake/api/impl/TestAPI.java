package com.github.sakizciadam.snake.api.impl;

import com.github.sakizciadam.snake.api.API;
import com.github.sakizciadam.snake.api.data.EnumStage;

import java.awt.*;

public class TestAPI extends API {

    @Override
    public void onMove(EnumStage enumStage, int[] x, int[] y) {
        super.onMove(enumStage, x, y);
        //Write your code here
    }

    @Override
    public void onRender(EnumStage enumStage, final Graphics graphics){
        super.onRender(enumStage,graphics);
        //Write your code here
    }

}
