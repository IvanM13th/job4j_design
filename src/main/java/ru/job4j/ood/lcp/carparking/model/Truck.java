package ru.job4j.ood.lcp.carparking.model;

public class Truck extends Vehicle {
    private final int size;

    public Truck(int regNumber, int size) {
        super(regNumber);
        if (size <= PassengerCar.SIZE) {
            throw new IllegalArgumentException("The size of a truck cannot be less than 1");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
