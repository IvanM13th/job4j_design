package ru.job4j.ood.lcp.carparking.parking;

import ru.job4j.ood.lcp.carparking.model.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class FirstParking implements Parking {
    private final List<Vehicle> passengerCars;

    private final List<Vehicle> trucks;
    private final int passengerPlaces;
    private final int trucksPlaces;

    public FirstParking(List<Vehicle> passengerCars, int passengerPlaces, List<Vehicle> trucks, int trucksPlaces) {
        this.passengerCars = new ArrayList<>(passengerCars);
        this.passengerPlaces = passengerPlaces;
        this.trucks = new ArrayList<>(trucksPlaces);
        this.trucksPlaces = trucksPlaces;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }
}