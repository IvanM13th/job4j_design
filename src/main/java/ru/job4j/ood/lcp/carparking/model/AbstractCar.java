package ru.job4j.ood.lcp.carparking.model;

public class AbstractCar implements Car {
    protected String type;
    protected int id;

    public AbstractCar(String type, int id) {
        this.type = type;
        this.id = id;
    }
}
