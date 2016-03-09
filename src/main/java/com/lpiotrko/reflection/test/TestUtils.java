package com.lpiotrko.reflection.test;

import com.lpiotrko.reflection.dao.Phone;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestUtils {

    private static final String FIELD_NAME = "operatingSystem";
    private static final String METHOD_NAME = "setMake";


    private static Phone phone = new Phone();

    public static void getPublicValueByReflection(long loopCircCount, Class objectClass, Object objectInstance) {
        String operatingSystem;
        try {
            Field publicStringField;
            for (int i = 0; i < loopCircCount; i++) {
                publicStringField = objectClass.getDeclaredField(FIELD_NAME);
                operatingSystem = (String) publicStringField.get(objectInstance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPublicValueByReflection(long loopCircCount, Class objectClass, Object objectInstance) {
        try {
            Field publicStringField;
            for (int i = 0; i < loopCircCount; i++) {
                publicStringField = objectClass.getDeclaredField(FIELD_NAME);
                publicStringField.set(objectInstance, "Windows Phone");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runPublicMethodWithArgumentByReflection(long loopCircCount, Class objectClass, Object objectInstance) {
        try {
            Method setPrimitiveInt;
            for (int i = 0; i < loopCircCount; i++) {
                setPrimitiveInt = objectClass.getDeclaredMethod(METHOD_NAME, String.class);
                setPrimitiveInt.invoke(objectInstance, "Microsoft");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runPublicMethod(long loopCircCount) {
        for (int i = 0; i < loopCircCount; i++) {
            phone.setMake("Microsoft");
        }
    }

    public static void getPublicValue(long loopCircCount) {
        String operatingSystem;

        for (int i = 0; i < loopCircCount; i++) {
            operatingSystem = phone.operatingSystem;
        }
    }

    public static void setPublicValue(long loopCircCount) {
        for (int i = 0; i < loopCircCount; i++) {
            phone.operatingSystem = "Windows Phone";
        }
    }
}
