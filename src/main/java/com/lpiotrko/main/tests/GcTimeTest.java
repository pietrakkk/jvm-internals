package com.lpiotrko.main.tests;

import com.lpiotrko.main.Constant;
import com.lpiotrko.main.utils.MemoryEater;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GcTimeTest implements Runnable {
    private static boolean continueMemoryAllocating;
    private static long allocationsCount = 0;
    private static boolean constantSize;

    public static void runBenchmarkTest(int threadsCount, boolean constantSize, int sleepTime) throws InterruptedException {
        setDefaultValues();
        GcTimeTest.constantSize = constantSize;

        ExecutorService taskExecutor = Executors.newFixedThreadPool(threadsCount);

        for (int i = 0; i < threadsCount; i++) {
            taskExecutor.execute(new GcTimeTest());
        }

        taskExecutor.shutdown();

        try {
            taskExecutor.awaitTermination(sleepTime, TimeUnit.MILLISECONDS);
            continueMemoryAllocating = false;
            System.out.println(String.format(Constant.OBJECTS_CREATED_MSG, threadsCount, constantSize, allocationsCount, (sleepTime / 1000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void setDefaultValues() {
        continueMemoryAllocating = true;
        allocationsCount = 0;
    }

    @Override
    public void run() {
        while (continueMemoryAllocating) {
            MemoryEater.allocate(GcTimeTest.constantSize);
            allocationsCount++;
        }
    }
}
