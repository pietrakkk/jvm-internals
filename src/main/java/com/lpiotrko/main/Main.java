package com.lpiotrko.main;

import com.lpiotrko.main.tests.GcAllocationsTest;
import com.lpiotrko.main.tests.GcTimeTest;

import java.util.Scanner;

public class Main {
    private static final int TIME = 30000;
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_CHOICES_NUMBER = 3;


    public static void main(String[] args) throws InterruptedException {
        runBenchmarkTests();
    }

    public static void runBenchmarkTests() throws InterruptedException {
        int userChoice = 0;
        boolean validChoice = false;
        int numberOfUserChoices = 1;

        while (numberOfUserChoices != MAX_CHOICES_NUMBER && !validChoice) {
            System.out.println(Constant.MENU);
            System.out.print(Constant.USER_CHOICE);
            userChoice = scanner.nextInt();

            validChoice = true;
            switch (userChoice) {
                case 11:
                    GcAllocationsTest.runBenchmarkTest(1, true);
                    break;
                case 12:
                    GcTimeTest.runBenchmarkTest(1, true, TIME);
                    break;
                case 21:
                    GcAllocationsTest.runBenchmarkTest(10, true);
                    break;
                case 22:
                    GcTimeTest.runBenchmarkTest(10, true, TIME);
                    break;
                case 31:
                    GcAllocationsTest.runBenchmarkTest(1, false);
                    break;
                case 32:
                    GcTimeTest.runBenchmarkTest(1, false, TIME);
                    break;
                case 41:
                    GcAllocationsTest.runBenchmarkTest(1, false);
                    break;
                case 42:
                    GcTimeTest.runBenchmarkTest(10, false, TIME);
                    break;
                default:
                    validChoice = false;
                    break;
            }

            if(!validChoice){
                numberOfUserChoices += 1;
                System.out.println(Constant.INVALID_USER_CHOICE);
            }
        }


    }
}
