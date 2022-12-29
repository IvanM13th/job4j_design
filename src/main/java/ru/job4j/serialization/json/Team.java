package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
public class Team {
    @XmlAttribute
    private String team;

    public Team() {

    }

    public Team(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return team;
    }
}
