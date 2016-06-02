package com.lpiotrko.serializer;

import java.io.*;

public class JavaSerializer {
    String FILE_NAME = "serialized.txt";


    public void serializeObject(Object object) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME));

        out.writeObject(object);
        out.close();
    }


    public Object deserializeObject() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(FILE_NAME));
        Object result = in.readObject();
        in.close();

        return result;
    }

}
