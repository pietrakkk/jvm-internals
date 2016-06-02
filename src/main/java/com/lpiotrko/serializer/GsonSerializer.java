package com.lpiotrko.serializer;


import com.google.gson.Gson;
import com.lpiotrko.Login;

import java.util.List;

public class GsonSerializer extends BaseSerializer {

    @Override
    public long serialize(List<Login> logins) {
        Gson gsonParser = new Gson();
        return 0;
    }

    @Override
    public long deserialize() {
        return 0;
    }
}
