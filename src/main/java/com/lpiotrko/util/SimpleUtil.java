package com.lpiotrko.util;

public class SimpleUtil {
    public static int sleepForParamTime(String time){
        int ms = Integer.parseInt(time);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ms;
    }
}
