package ru.job4j.ood.lcp.carparking.parking;

import ru.job4j.ood.lcp.carparking.model.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class FirstParking implements Parking {
    private final List<Vehicle> passengerCars;

    private final List<Vehicle> trucks;
    private final int passengerPlaces;
    private final int trucksPlaces;

    public FirstParking(int passengerPlaces, int trucksPlaces) {
        this.passengerPlaces = passengerPlaces;
        this.trucksPlaces = trucksPlaces;
        this.passengerCars = new ArrayList<>(passengerPlaces);
        this.trucks = new ArrayList<>(trucksPlaces);

    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }

    public List<Vehicle> getPassengerCars() {
        return passengerCars;
    }

    public List<Vehicle> getTrucks() {
        return trucks;
    }

    public int getPassengerPlaces() {
        return passengerPlaces;
    }

    public int getTrucksPlaces() {
        return trucksPlaces;
    }
}
