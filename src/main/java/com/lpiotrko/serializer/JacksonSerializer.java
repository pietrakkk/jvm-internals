package com.lpiotrko.serializer;


import com.lpiotrko.Login;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JacksonSerializer extends BaseSerializer {

    @Override
    public long serialize(List<Login> logins) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(System.out, logins);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long deserialize() {
        ObjectMapper mapper = new ObjectMapper();

        return 0;
    }
}
