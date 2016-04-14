package com.lpiotrko.util;


public class AnotherSImpleUtil {

    public static int sleepForParamTime(String time){
        int ms = Integer.parseInt(time);
        try {
            Thread.sleep(ms*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ms;
    }
}
