package com.lpiotrko.reflection.main;

import com.lpiotrko.reflection.dao.Contact;
import com.lpiotrko.reflection.dao.Phone;
import com.lpiotrko.reflection.jsonparser.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParser();
        Phone phone = generateRandomData();

        String jsonString = jsonParser.parseObjectToJsonString(phone);
        String jsonStringByJackson = convertObjectToJsonString(phone);

        System.out.println(String.format("Test poprawności rozwiązania dla obiektu klasy Phone: %b",
                jsonString.equals(jsonStringByJackson)));
    }



    public static Phone generateRandomData(){
        Phone phone = new Phone();

        List<Contact> contacts = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            Contact contact = new Contact();
            contact.setName("Łukasz");
            contact.setSurname("Piotrkowski");
            contact.setNumber("511739689");

            contacts.add(contact);
        }

        phone.setId(1L);
        phone.setYop(2015);
        phone.setModel("3310");
        phone.setMake("Nokia");
        phone.setContacts(contacts);

        return phone;
    }


    public static String convertObjectToJsonString(Object objectInstance) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(objectInstance);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
