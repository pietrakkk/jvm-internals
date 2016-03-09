package com.lpiotrko.reflection;


import com.lpiotrko.reflection.test.TestUtils;
import com.lpiotrko.reflection.test.TestsResults;

public class ReflectionPerformanceTest {

    public static void main(String[] args) {
        final String CLASS_SOURCE = "com.lpiotrko.reflection.dao.Phone";
        final long LOOP_CIRC_COUNT = 10000;
        final long GLOBAL_LOOP_CIRC_COUNT = 100;


        for (int i = 0; i < GLOBAL_LOOP_CIRC_COUNT; i++) {
            runNativeTests(LOOP_CIRC_COUNT);
            runReflectionTests(CLASS_SOURCE, LOOP_CIRC_COUNT);
        }

        TestsResults.showTestsResults();
    }


    public static void runReflectionTests(String classSource, long loopCircCount) {
        Class phoneClass = null;

        try {
            phoneClass = Class.forName(classSource);
            Object phoneObject = phoneClass.newInstance();
            runReflectionLoops(loopCircCount, phoneClass, phoneObject);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void runNativeTests(long loopCircCount) {
        Long testStartTime, testEndTime;

        testStartTime = System.nanoTime();
        TestUtils.runPublicMethod(loopCircCount);
        testEndTime = System.nanoTime();
        TestsResults.runPublicMethodRes.add(testEndTime - testStartTime);

        testStartTime = System.nanoTime();
        TestUtils.getPublicValue(loopCircCount);
        testEndTime = System.nanoTime();
        TestsResults.getPublicValueRes.add(testEndTime - testStartTime);

        testStartTime = System.nanoTime();
        TestUtils.setPublicValue(loopCircCount);
        testEndTime = System.nanoTime();
        TestsResults.setPublicValueRes.add(testEndTime - testStartTime);
    }


    private static void runReflectionLoops(long numberOfInvokes, Class objectClass, Object objectInstance) {
        long testStartTime, testEndTime;

        testStartTime = System.nanoTime();
        TestUtils.runPublicMethodWithArgumentByReflection(numberOfInvokes, objectClass, objectInstance);
        testEndTime = System.nanoTime();
        TestsResults.runPublicMethodReflectionRes.add(testEndTime - testStartTime);

        testStartTime = System.nanoTime();
        TestUtils.getPublicValueByReflection(numberOfInvokes, objectClass, objectInstance);
        testEndTime = System.nanoTime();
        TestsResults.getPublicValuesWithReflectionRes.add(testEndTime - testStartTime);

        testStartTime = System.nanoTime();
        TestUtils.setPublicValueByReflection(numberOfInvokes, objectClass, objectInstance);
        testEndTime = System.nanoTime();
        TestsResults.setPublicValuesWithReflectionRes.add(testEndTime - testStartTime);
    }
}