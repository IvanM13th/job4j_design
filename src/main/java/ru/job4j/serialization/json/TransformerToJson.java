package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TransformerToJson {
    public static void main(String[] args) {
        final Transformer tr = new Transformer(
                true,
                10,
                "DobriiUBIVATOR",
                new Team("Autobot"),
                new String[] {"Earth", "Mars", "Cybertron"}
        );

        final Gson gson = new GsonBuilder().create();
        final String trToJson = gson.toJson(tr);
        System.out.println(trToJson);

        final Transformer trFromJson = gson.fromJson(trToJson, Transformer.class);
        System.out.println(trFromJson);
    }
}
