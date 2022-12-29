package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
/*    public static void main(String[] args) {
        final Person person = new Person(false, 32, new Contact("123-321"),
                new String[]{"Self-employed", "Married"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        final String personJson =
                "{"
                + "\"sex\":false,"
                + "\"age\":35,"
                + "\"contact\":"
                + "{"
                + "\"phone\":\"+7(999)999-99-00\""
                + "},"
                + "\"statuses\":"
                + "[\"Student\",\"Free\"]"
                + "}";

        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }*/

    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        final Person person = new Person(false, 30, new Contact("11-111"), new String[] {"Worker", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(person).toString());
    }
}