package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "transformer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transformer {
    @XmlAttribute
    private boolean colorRed;
    @XmlAttribute
    private int weaponsCount;
    private String name;
    private Team team;
    @XmlElementWrapper(name = "visitedPlanets")
    @XmlElement(name = "planet")
    private String[] visitedPlanets;

    public Transformer() {

    }

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
