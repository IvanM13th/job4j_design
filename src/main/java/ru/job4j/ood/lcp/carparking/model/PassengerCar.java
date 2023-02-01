package ru.job4j.ood.lcp.carparking.model;

public class PassengerCar extends Vehicle {
    public static final int SIZE = 1;

    public PassengerCar(int regNumber) {
        super(regNumber);
    }

    @Override
    public int getSize() {
        return SIZE;
    }
}