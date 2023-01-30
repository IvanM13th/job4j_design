package ru.job4j.ood.lcp.carparking;

import ru.job4j.ood.lcp.carparking.model.Vehicle;
import ru.job4j.ood.lcp.carparking.parking.Parking;

public class Administrator {
    private final Parking parking;
    public static final int SIZE = 1;

    public Administrator(Parking parking) {
        this.parking = parking;
    }

    public boolean checkEmptyPlaces(Vehicle vehicle) {
        return false;
    }

    public boolean placeCar(Vehicle vehicle) {
        return false;
    }
}