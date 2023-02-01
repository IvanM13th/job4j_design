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
        int size = findSize(vehicle);
        return size == 1
                ? placePCar(vehicle)
                : placeTruck(vehicle, size);
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

    private int findSize(Vehicle vehicle) {
        return vehicle.getSize();
    }

    private boolean placePCar(Vehicle vehicle) {
        boolean placed = false;
        if (validatePlaces(passengerCars, passengerPlaces)) {
            placed = passengerCars.add(vehicle);
        }
        return placed;
    }

    private boolean placeTruck(Vehicle vehicle, int size) {
        boolean placed = false;
        if (validatePlaces(trucks, trucksPlaces)) {
            placed = trucks.add(vehicle);
        } else {
            if (checkPlacesForTruckAtPPlaces(passengerPlaces, size)) {
                int sizeCounter = 1;
                while (sizeCounter <= size) {
                    passengerCars.add(vehicle);
                    sizeCounter++;
                }
                placed = (sizeCounter - 1) == size;
            }
        }
        return placed;
    }

    private boolean validatePlaces(List<Vehicle> list, int initialPlaces) {
        return list.size() < initialPlaces;
    }

    private boolean checkPlacesForTruckAtPPlaces(int parkingPlaces, int truckSize) {
        return (parkingPlaces - passengerCars.size() - truckSize) >= 0;
    }
}