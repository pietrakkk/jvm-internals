package com.lpiotrko.serializer;

import com.lpiotrko.Login;

import java.util.List;

public abstract class BaseSerializer {

    public void testSerialization(){
    }

    public void testDeserialization(){
    }

    public abstract long serialize(List<Login> logins);
    public abstract long  deserialize();

}
