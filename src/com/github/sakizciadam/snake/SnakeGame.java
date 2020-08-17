package com.github.sakizciadam.snake;

import com.github.sakizciadam.snake.api.API;
import com.github.sakizciadam.snake.api.APIManager;
import com.github.sakizciadam.snake.utils.FPSCounter;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;

public class SnakeGame extends JFrame {

    private FPSCounter fpsCounter;
    private static SnakeGame INSTANCE;
    private static API api;
    private ScreenPanel screenPanel;
    private static APIManager apiManager;
    private static boolean debug;

    protected SnakeGame() {
        INSTANCE=this;
        if(!debug)
            initScreen();
        else
            initDebugScreen();
    }

    private void initDebugScreen() {
        info("Setting up ScreenPanel");
        screenPanel=new ScreenPanel();
        info("Implementing APIManager");
        apiManager=new APIManager();
        info("Adding ScreenPanel to main class");
        add(screenPanel);

        setResizable(false);
        info("Resizable setted to "+this.isResizable());
        pack();

        setTitle("Snake DEBUG MODE");
        info("Setting title");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        if(args.length==1){
            if(args[0].equalsIgnoreCase("--debug")){
                debug=true;
            }
        }
        EventQueue.invokeLater(() -> {
            JFrame frame = new SnakeGame();
            frame.setVisible(true);
        });
    }

    protected static SnakeGame getInstance() {
        return INSTANCE;
    }

    protected void info(String message){
        final Date date = Calendar.getInstance().getTime();
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        message="["+dateFormat.format(date)+"] [INFO] >> "+message;
        System.out.println(message);
    }

    protected void error(String message){
        final Date date = Calendar.getInstance().getTime();
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        message="["+dateFormat.format(date)+"] [ERROR] >> "+message;
        System.out.println(message);
    }

    public boolean isGameRunningOnDebugMode(){
        return debug;
    }


    protected ScreenPanel getPanel(){
        return screenPanel;
    }

    public APIManager getApiManager(){
        return apiManager;
    }






}
