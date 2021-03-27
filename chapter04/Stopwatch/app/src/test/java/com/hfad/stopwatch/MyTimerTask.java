package com.hfad.stopwatch;

import java.util.TimerTask;

/**
 * @author admin  on 2019/3/7.
 */
class MyTimerTask extends TimerTask {
    private int time = 0;

    MyTimerTask() {
    }


    @Override
    public void run() {
        System.out.println("time = " + time);
        time++;
    }
}
