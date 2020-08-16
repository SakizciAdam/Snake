package com.github.sakizciadam.snake;

import com.github.sakizciadam.snake.api.API;
import com.github.sakizciadam.snake.api.data.EnumStage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ScreenPanel extends JPanel implements ActionListener {

    private final int SCREEN_WIDTH = 300;
    private final int SCREEN_HEIGHT = 300;
    private final int DOT_ICON_SIZE = 10;
    private final int DOT_ICONS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[DOT_ICONS];
    private final int y[] = new int[DOT_ICONS];

    private int parts;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inSnakeGame = true;

    private Timer timer;




    private static ScreenPanel instance;

    private GameRunner gameRunner;

    public ScreenPanel() {
        
        initScreenPanel();
    }
    
    private void initScreenPanel() {
        instance=this;
        gameRunner=new GameRunner();
        addKeyListener(new GameKeyAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        initSnakeGame();
    }



    private void initSnakeGame() {

        inSnakeGame=true;
        parts = 5;

        for (int z = 0; z < parts; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        
        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(API api : SnakeGame.getInstance().getApiManager().getAPI()){
            api.onRender(EnumStage.PRE,g);
        }
        doDrawing(g);
        gameRunner.frame();
        fps(g);
        for(API api : SnakeGame.getInstance().getApiManager().getAPI()){
            api.onRender(EnumStage.POST,g);
        }

    }
    
    private void doDrawing(Graphics g) {
        
        if (inSnakeGame) {
            score(g);
            g.setColor(Color.red);
            g.fillRect(apple_x,apple_y,8,8);

            for (int z = 0; z < parts; z++) {
                if (z == 0) {
                    g.setColor(Color.blue);
                    g.fillRect(x[z],y[z],8,8);
                } else {
                    g.setColor(Color.cyan);
                    g.fillRect(x[z],y[z],8,8);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            endGame(g);
        }        
    }

    private void score(Graphics g) {

        final String score = "Score "+String.valueOf((parts-5)*100);
        final Font font1 = new Font("Helvetica", Font.BOLD, 14);
        final FontMetrics metr1 = getFontMetrics(font1);

        g.setColor(Color.white);
        g.setFont(font1);
        g.drawString(score, ((SCREEN_WIDTH - metr1.stringWidth(score)) / 2)-100, 20);


    }

    private void fps(Graphics g) {

        final String fps = gameRunner.getFPS();
        final Font font1 = new Font("Helvetica", Font.BOLD, 14);
        final FontMetrics metr1 = getFontMetrics(font1);

        g.setColor(Color.white);
        g.setFont(font1);
        g.drawString(fps, (SCREEN_WIDTH - metr1.stringWidth(fps)) / 2, 20);


    }

    private void endGame(Graphics g) {
        
        final String msg1 = "Game Over";
        final Font font1 = new Font("Helvetica", Font.BOLD, 14);
        final FontMetrics metr1 = getFontMetrics(font1);

        g.setColor(Color.white);
        g.setFont(font1);
        g.drawString(msg1, (SCREEN_WIDTH - metr1.stringWidth(msg1)) / 2, SCREEN_HEIGHT / 2);

        final String msg2 = "Press Enter to Restart";
        final Font font2 = new Font("Helvetica", Font.BOLD, 12);
        final FontMetrics metr2 = getFontMetrics(font2);

        g.setColor(Color.white);
        g.setFont(font2);
        g.drawString(msg2, (SCREEN_WIDTH - metr2.stringWidth(msg2)) / 2, SCREEN_HEIGHT / 2+30);
    }

    public void findAppleIcon() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            parts++;
            locateApple();
        }
    }

    private void shift() {
        for(API api : SnakeGame.getInstance().getApiManager().getAPI()){
            api.onMove(EnumStage.PRE,x,y);
        }

        for (int z = parts; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_ICON_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_ICON_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_ICON_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_ICON_SIZE;
        }
        for(API api : SnakeGame.getInstance().getApiManager().getAPI()){
            api.onMove(EnumStage.POST,x,y);
        }
    }

    public void findCollision() {

        for (int z = parts; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inSnakeGame = false;
            }
        }

        if (y[0] >= SCREEN_HEIGHT) {
            inSnakeGame = false;
        }

        if (y[0] < 0) {
            inSnakeGame = false;
        }

        if (x[0] >= SCREEN_WIDTH) {
            inSnakeGame = false;
        }

        if (x[0] < 0) {
            inSnakeGame = false;
        }
        
        if (!inSnakeGame) {
            timer.stop();
        }
    }

    public void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_ICON_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_ICON_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (inSnakeGame) {

            shift();
        }


    }

    protected void down(){
        if(upDirection)
            return;
        downDirection = true;
        rightDirection = false;
        leftDirection = false;
    }

    protected void up(){
        if(downDirection)
            return;
        upDirection = true;
        rightDirection = false;
        leftDirection = false;
    }

    protected void right(){
        if(leftDirection)
            return;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
    }

    protected void left(){
        if(rightDirection)
            return;
        leftDirection = true;
        upDirection = false;
        downDirection = false;
    }

    private class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {

            int key = event.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                left();
            }

            if (key == KeyEvent.VK_RIGHT) {
                right();
            }

            if (key == KeyEvent.VK_UP) {
                up();
            }

            if (key == KeyEvent.VK_DOWN) {
                down();
            }
            if(key==KeyEvent.VK_ENTER){
                initSnakeGame();
            }
        }
    }

    public static ScreenPanel getInstance() {
        return instance;
    }

    public boolean isGameRunning(){
        return inSnakeGame;
    }
}
