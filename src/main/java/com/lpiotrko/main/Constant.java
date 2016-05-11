package com.lpiotrko.main;


public class Constant {
    public static final String MENU = "Menu:\n\n"
            + "Test z alokacją na 1 wątku ze stałym rozmiarem obiektów\n"
            + "11 ------> Ilość czasu do zaalokowaniu x obiektów\n"
            + "12 ------> Ilość alokacji w ciągu 30 sekund\n"

            + "Test z alokacją na 10 wątkach ze stałym rozmiarem obiektów\n"
            + "21 ------> Ilość czasu do zaalokowaniu x obiektów.\n"
            + "22 ------> Ilość alokacji w ciągu 30 sekund.\n"

            + "Test z alokacją na 1 wątku z dynamicznym rozmiarem obiektów\n"
            + "31 ------> Ilość czasu do zaalokowaniu x obiektów\n"
            + "32 ------> Ilość alokacji w ciągu 30 sekund\n"

            + "Test z alokacją na 10 wątkach z dynamicznym rozmiarem obiektów\n"
            + "41 ------> Ilość czasu do zaalokowaniu x obiektów.\n"
            + "42 ------> Ilość alokacji w ciągu 30 sekund\n";
    public static final String USER_CHOICE = "Twój wybór: ";
    public static final String INVALID_USER_CHOICE = "Nieprawidłowy numer!";
    public static final String GC_ALLOCATIONS_RESULT_MSG = "REZULTAT: Ilość wątków: %s, stała ilość obiektów : %s,  czas alokacji dla %s obiektów: %s ms.";
    public static final String OBJECTS_CREATED_MSG = "REZULTAT: Ilość wątków: %s, stała ilość obiektów: %s: %s obiektów stworzonych w %s sekund.";
}
