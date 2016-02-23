package com.lpiotrko.main;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int elementsCounter = 0;

    public static void main(String[] args) {

        try {
            testOutOfMemory();
        } catch (OutOfMemoryError exception) {
            System.out.println(elementsCounter + " elements added to 'sampleList' so far.");
            exception.printStackTrace();
        }
    }

    private static void testOutOfMemory() {

        List<Integer> sampleList = new ArrayList<>();

        while (true) {
            sampleList.add(1);
            elementsCounter += 1;
        }
    }
}
