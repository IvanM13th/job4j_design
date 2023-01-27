package ru.job4j.ood.lcp.carparking.parking;

import ru.job4j.ood.lcp.carparking.model.Car;

public class FirstParking implements Parking {

    @Override
    public boolean placePassengerCar(Car car) {
        return false;
    }

    @Override
    public boolean placeTruck(Car car) {
        return false;
    }
}
