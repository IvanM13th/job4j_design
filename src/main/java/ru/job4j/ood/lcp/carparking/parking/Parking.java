package ru.job4j.ood.lcp.carparking.parking;

import ru.job4j.ood.lcp.carparking.model.Car;

public interface Parking {

    boolean placePassengerCar(Car car);

    boolean placeTruck(Car car);
}
