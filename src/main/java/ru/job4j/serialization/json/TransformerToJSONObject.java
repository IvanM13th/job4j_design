package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransformerToJSONObject {
    public static void main(String[] args) {
        JSONObject jsonTeam = new JSONObject("{\"team\" : \"Autobot\"}");
        List<String> visitedPlanets = new ArrayList<>();
        visitedPlanets.add("Earth");
        visitedPlanets.add("Mars");
        visitedPlanets.add("Moon");
        JSONArray jsonPlanets = new JSONArray(visitedPlanets);

        final Transformer tr = new Transformer(
                true,
                10,
                "DobriiUBIVATOR",
                new Team("Autobot"),
                new String[] {"Earth", "Mars", "Cybertron"}
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("colorRed", tr.isColorRed());
        jsonObject.put("weaponCount", tr.getWeaponsCount());
        jsonObject.put("name", tr.getName());
        jsonObject.put("team", jsonTeam);
        jsonObject.put("visitedPlanets", visitedPlanets);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(tr).toString());

    }
}
