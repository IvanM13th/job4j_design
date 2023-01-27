package ru.job4j.ood.lcp.carparking;

import ru.job4j.kiss.Parent;
import ru.job4j.ood.lcp.carparking.model.Car;
import ru.job4j.ood.lcp.carparking.parking.Parking;

public class Administrator {
    private final Parking parking;

    public Administrator(Parking parking) {
        this.parking = parking;
    }

    public boolean checkEmptyPlaces(Car car) {
        return false;
    }

    public boolean placeCar(Car car) {
        return false;
    }
}
