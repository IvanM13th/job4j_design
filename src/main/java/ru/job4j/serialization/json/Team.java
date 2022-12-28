package ru.job4j.serialization.json;

public class Team {
    private final String team;

    public Team(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return team;
    }
}
