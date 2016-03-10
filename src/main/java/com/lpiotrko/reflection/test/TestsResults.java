package com.lpiotrko.reflection.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestsResults {
    public static List<Long> getPublicValuesWithReflectionRes = new ArrayList<>();
    public static List<Long> setPublicValuesWithReflectionRes = new ArrayList<>();
    public static List<Long> runPublicMethodReflectionRes = new ArrayList<>();
    public static List<Long> runPublicMethodRes = new ArrayList<>();
    public static List<Long> getPublicValueRes = new ArrayList<>();
    public static List<Long> setPublicValueRes = new ArrayList<>();

    public static void showTestsResults() {
        printSingleResults();
        removeWorstAndBestResults();
        System.out.println("TEST RESULTS: ");
        System.out.println(String.format("Get public value native result: %d ns", getAvgTestsResult(getPublicValueRes)));
        System.out.println(String.format("Get public value with reflection result: %d ns", getAvgTestsResult(getPublicValuesWithReflectionRes)));
        System.out.println(String.format("Set public value native result: %d ns", getAvgTestsResult(setPublicValueRes)));
        System.out.println(String.format("Set public value with reflection result: %d ns", getAvgTestsResult(setPublicValuesWithReflectionRes)));
        System.out.println(String.format("Run public method native result: %d ns", getAvgTestsResult(runPublicMethodRes)));
        System.out.println(String.format("Run public method with reflection result: %d ns", getAvgTestsResult(runPublicMethodReflectionRes)));
    }


    private static long getAvgTestsResult(List<Long> resultsList) {
        return (long) resultsList.stream().mapToLong(el -> el.longValue()).average().getAsDouble();
    }

    private static void printSingleResults() {
        printTestSingleResult(getPublicValueRes, "GET PUBLIC VALUE:");
        printTestSingleResult(getPublicValuesWithReflectionRes, "GET PUBLIC VALUE WITH REF:");
        printTestSingleResult(setPublicValueRes, "SET PUBLIC VALUE:");
        printTestSingleResult(setPublicValuesWithReflectionRes, "SET PUBLIC VALUE  WITH REF:");
        printTestSingleResult(runPublicMethodRes, "RUN PUBLIC METHOD:");
        printTestSingleResult(runPublicMethodReflectionRes, "RUN PUBLIC METHOD WITH REF:");
    }

    public static void printTestSingleResult(List<Long> list, String testName) {
        System.out.println(testName);
        for (Long item : list) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------------");
    }

    private static void removeWorstAndBestResults() {
        getPublicValueRes.remove(getPublicValueRes.indexOf(Collections.min(getPublicValueRes)));
        getPublicValueRes.remove(getPublicValueRes.indexOf(Collections.max(getPublicValueRes)));
        setPublicValueRes.remove(setPublicValueRes.indexOf(Collections.min(setPublicValueRes)));
        setPublicValueRes.remove(setPublicValueRes.indexOf(Collections.max(setPublicValueRes)));
        runPublicMethodRes.remove(runPublicMethodRes.indexOf(Collections.min(runPublicMethodRes)));
        runPublicMethodRes.remove(runPublicMethodRes.indexOf(Collections.max(runPublicMethodRes)));
        runPublicMethodReflectionRes.remove(runPublicMethodReflectionRes.indexOf(Collections.min(runPublicMethodReflectionRes)));
        runPublicMethodReflectionRes.remove(runPublicMethodReflectionRes.indexOf(Collections.max(runPublicMethodReflectionRes)));
        getPublicValuesWithReflectionRes.remove(getPublicValuesWithReflectionRes.indexOf(Collections.min(getPublicValuesWithReflectionRes)));
        getPublicValuesWithReflectionRes.remove(getPublicValuesWithReflectionRes.indexOf(Collections.max(getPublicValuesWithReflectionRes)));
        setPublicValuesWithReflectionRes.remove(setPublicValuesWithReflectionRes.indexOf(Collections.min(setPublicValuesWithReflectionRes)));
        setPublicValuesWithReflectionRes.remove(setPublicValuesWithReflectionRes.indexOf(Collections.max(setPublicValuesWithReflectionRes)));
    }
}
