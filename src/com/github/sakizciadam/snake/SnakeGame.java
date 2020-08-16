package com.github.sakizciadam.snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class SnakeGame extends JFrame {

    private FPSCounter  fpsCounter;
    private static SnakeGame INSTANCE;
    private static API api;
    private ScreenPanel screenPanel;

    public SnakeGame() {
        INSTANCE=this;
        initScreen();
    }
    
    private void initScreen() {
        api=new API(this);
        screenPanel=new ScreenPanel();
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

    public static API getAPI(){
        return api.getAPI();
    }

    protected ScreenPanel getPanel(){
        return screenPanel;
    }






}
