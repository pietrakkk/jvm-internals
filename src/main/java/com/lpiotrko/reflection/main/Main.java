package com.lpiotrko.reflection.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static final int NUMBER_OF_THREADS = 2;
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final int ITERATIONS_COUNT = 20;

    public static void main(String[] args) throws ExecutionException, InterruptedException, ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
        String dateString = "19910608";



        //use unsafe solution
        Callable<Date> task = () -> sf.parse(dateString);
        testSimpleDateFormat("Unsafe", ITERATIONS_COUNT, task);


        //use thread safety solution
        SimpleDateFormatSafety simpleDateFormatSafety = new SimpleDateFormatSafety();
        Callable<Date> safetyTask = () -> simpleDateFormatSafety.convertDate(dateString);
        testSimpleDateFormat("Safe", ITERATIONS_COUNT, safetyTask);

    }


    public static void testSimpleDateFormat(String testType, int iterationCount, Callable<Date> task){
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        List<Future<Date>> results = new ArrayList<>();

        for(int i = 0 ; i < iterationCount ; i++){
            results.add(executorService.submit(task));
        }
        executorService.shutdown();


        try {
            System.out.println();
            System.out.println(testType);
            for(Future<Date> result : results) {
                System.out.println(result.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

