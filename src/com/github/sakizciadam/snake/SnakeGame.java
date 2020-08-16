package com.github.sakizciadam.snake;

import com.github.sakizciadam.snake.api.API;
import com.github.sakizciadam.snake.api.APIManager;
import com.github.sakizciadam.snake.utils.FPSCounter;

import java.awt.*;
import javax.swing.JFrame;

public class SnakeGame extends JFrame {

    private FPSCounter fpsCounter;
    private static SnakeGame INSTANCE;
    private static API api;
    private ScreenPanel screenPanel;
    private static APIManager apiManager;

    public SnakeGame() {
        INSTANCE=this;
        initScreen();
    }
    
    private void initScreen() {
        screenPanel=new ScreenPanel();
        apiManager=new APIManager();
        add(screenPanel);
               
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame frame = new SnakeGame();
            frame.setVisible(true);
        });
    }

    protected static SnakeGame getInstance() {
        return INSTANCE;
    }


    protected ScreenPanel getPanel(){
        return screenPanel;
    }

    protected APIManager getApiManager(){
        return apiManager;
    }






}
