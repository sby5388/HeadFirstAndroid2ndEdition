package com.hfad.stopwatch;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author admin  on 2019/3/7.
 */
public class TimerTest {
    @Test
    public void test() {
        // TODO: 2019/3/7 如果没有对线程设置存活时间，那么将看不到任何打印的结果
        Timer timer = new Timer();
        TimerTask timerTask = new MyTimerTask();
        timer.schedule(timerTask, 0, 1000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
    }


}
