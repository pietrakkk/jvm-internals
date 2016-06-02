package com.lpiotrko;

import com.lpiotrko.serializer.JavaSerializer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Login login = new Login();
        login.setUsername("lpiotrko");
        login.setPassword("lpiotrko");

        JavaSerializer serializer = new JavaSerializer();
        try {
            serializer.serializeObject(login);
            Login l = (Login) serializer.deserializeObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
