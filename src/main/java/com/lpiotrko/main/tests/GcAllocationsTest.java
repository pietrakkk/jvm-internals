package com.lpiotrko.main.tests;


import com.lpiotrko.main.Constant;
import com.lpiotrko.main.utils.MemoryEater;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GcAllocationsTest implements Runnable {

    private static final int DEFAULT_OBJECTS_COUNT = 500;
    private static final double SECONDS_DIVIDER = 1000000000.0;
    private static final int SLEEP_TIME = 20000;

    private static long startTime = 0;
    private static long endTime = 0;
    private static long totalObjectsCount = 0;
    private static boolean constantSize;
    private static int threadCount = 0;

    private static int threadsFinishedCounter = 0;

    public static void runBenchmarkTest(int threadsCount, boolean constantSize){
        setDefaultValues();
        GcAllocationsTest.constantSize = constantSize;
        GcAllocationsTest.threadCount = threadsCount;


        startTime = System.currentTimeMillis();

        ExecutorService taskExecutor = Executors.newFixedThreadPool(threadsCount);
        for (int i = 0; i < threadsCount; i++) {
            taskExecutor.execute(new GcAllocationsTest());
        }

        taskExecutor.shutdown();

        try {
            taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            endTime = System.currentTimeMillis();
            long resultTime = endTime - startTime;
            System.out.println(String.format(Constant.GC_ALLOCATIONS_RESULT_MSG,
                    threadsCount, GcAllocationsTest.constantSize, DEFAULT_OBJECTS_COUNT, resultTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void setDefaultValues(){
        startTime = 0;
        endTime = 0;
        totalObjectsCount = 0;
    }

    private static void allocObjects(int numberOfObjects) {
        while (totalObjectsCount <= numberOfObjects) {
            MemoryEater.allocate(constantSize);
            totalObjectsCount++;
        }

        threadsFinishedCounter++;
        if (threadsFinishedCounter == threadCount) {
            endTime = System.nanoTime();
        }
    }

    @Override
    public void run() {
        allocObjects(DEFAULT_OBJECTS_COUNT);
    }
}
