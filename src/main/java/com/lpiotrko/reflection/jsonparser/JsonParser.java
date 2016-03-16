package com.lpiotrko.reflection.jsonparser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class JsonParser {

    private static final String INVERTERD_COMMAS = "\"";
    private static final String ARRAY_START = "[";
    private static final String ARRAY_END = "]";
    private static final String COLON = ":";
    private static final String FIELD_START = "{";
    private static final String FIELD_END = "}";
    private static final String COMMA = ",";
    private static final String GETTER_START = "get";
    private static final String BOOLEAN_GETTER_START = "is";

    public String parseObjectToJsonString(Object object){
        StringBuilder jsonBuilder = new StringBuilder(FIELD_START);
        processObjectFields(object, jsonBuilder);
        jsonBuilder.append(FIELD_END);

        return jsonBuilder.toString();
    }

    private boolean hasValuePublicAccess(Method[] methods, String fieldName){
        boolean hasPublicAccess;
        String methodName;
        for (Method method : methods) {
            methodName = method.getName().toLowerCase();
            hasPublicAccess =
                    (methodName.startsWith(GETTER_START) || methodName.startsWith(BOOLEAN_GETTER_START)) &&
                    methodName.contains(fieldName);

            if (hasPublicAccess) {
                return true;
            }
        }
        return false;
    }

    private boolean isList(Field field) {
        return field.getType() == List.class;
    }

    private void processObjectFields(Object objectInstance, StringBuilder builder){
        if(objectInstance == null){return;}
        Class objectClass = objectInstance.getClass();
        Field[] objectFields = objectClass.getDeclaredFields();

        List<Field> fields =  Arrays.asList(objectFields);

        fields.forEach(field -> {
            if(hasValuePublicAccess(objectClass.getDeclaredMethods(), field.getName())){
                processSingleField(field, builder, objectInstance, fields.indexOf(field) != fields.size() - 1);
            }
        });
    }

    private boolean isCustomObjectType(Field field){
        return !field.getType().toString().startsWith("class java.") && !field.getType().isPrimitive();
    }

    private void processSingleField(Field field, StringBuilder builder, Object objectInstance, boolean isLastElement){
        try {
            field.setAccessible(true);
            builder.append(INVERTERD_COMMAS);
            builder.append(field.getName());
            builder.append(INVERTERD_COMMAS);
            builder.append(COLON);

            if (isList(field)){
                builder.append(ARRAY_START);
                Object list = field.get(objectInstance);
                convertToJsonArray(list, builder);
                builder.append(ARRAY_END);


            } else if(isCustomObjectType(field)){
                builder.append(FIELD_START);
                processObjectFields(field.get(objectInstance), builder);
                builder.append(FIELD_END);
                tryToAppendComma(isLastElement, builder);
            }else{
                prepareSimpleJsonValue(field, objectInstance, builder);
                tryToAppendComma(isLastElement, builder);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void convertToJsonArray(Object listOfObjects, StringBuilder builder){
        List<Object> objects = (List<Object>) listOfObjects;
        objects.forEach(object -> {
            builder.append(FIELD_START);
            processObjectFields(object, builder);
            builder.append(FIELD_END);
            tryToAppendComma(objects.indexOf(object) != objects.size() -1, builder);
        });
    }

    private void tryToAppendComma(boolean condition,StringBuilder builder){
        if(condition){
            builder.append(COMMA);
        }
    }

    private boolean isNumber(Field field){
        return field.getType() == Long.class ||
                field.getType() == Integer.class ||
                field.getType() == Double.class ||
                field.getType() == Float.class ||
                field.getType() == long.class ||
                field.getType() == int.class ||
                field.getType() == double.class ||
                field.getType() == float.class;
    }

    private void prepareSimpleJsonValue(Field field, Object object, StringBuilder builder){
        try {
            String fieldValue;
            if(isNumber(field)){
               builder.append(field.get(object));
            }else{
                builder.append(INVERTERD_COMMAS);
                fieldValue = (field.get(object) != null) ? field.get(object).toString() : "";
                builder.append(fieldValue);
                builder.append(INVERTERD_COMMAS);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
