package com.github.sakizciadam.snake.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FPSCounter implements ActionListener {
    private final Timer resetTimer;
    private int current, last;

    public FPSCounter() {
        resetTimer = new Timer(1000, this);
    }

    public synchronized void start() {
        resetTimer.start();
        current = 0;
        last = -1;
    }

    public synchronized void stop() {
        resetTimer.stop();
        current = -1;
    }

    public synchronized void frame() {
        ++current;
    }

    @Override
    public synchronized void actionPerformed(final ActionEvent e) {
        last = current;
        current = 0;
    }

    public synchronized int getLast() {
        return last;
    }

    public synchronized int getCurrent() {
        return current;
    }
}