package ru.job4j.ood.lcp.carparking.model;

public abstract class Vehicle {

    protected int regNumber;

    public Vehicle(int regNumber) {
        this.regNumber = regNumber;
    }

    public abstract int getSize();

}