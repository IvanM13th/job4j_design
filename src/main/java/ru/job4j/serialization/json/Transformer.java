package ru.job4j.serialization.json;

import java.util.Arrays;

public class Transformer {
    private final boolean colorRed;
    private int weaponsCount;
    private final String name;
    private Team team;
    private String[] visitedPlanets;

    public Transformer(boolean colorRed, int weaponsCount, String name, Team team, String[] visitedPlanets) {
        this.colorRed = colorRed;
        this.weaponsCount = weaponsCount;
        this.name = name;
        this.team = team;
        this.visitedPlanets = visitedPlanets;
    }

    @Override
    public String toString() {
        return "Transformer{" + "colorRed=" + colorRed
                + ", weaponsCount=" + weaponsCount
                + ", name='" + name + '\''
                + ", team=" + team
                + ", visitedPlanets=" + Arrays.toString(visitedPlanets)
                + '}';
    }
}
