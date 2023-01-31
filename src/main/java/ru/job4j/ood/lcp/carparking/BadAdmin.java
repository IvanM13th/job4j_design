package ru.job4j.ood.lcp.carparking;

import ru.job4j.ood.lcp.carparking.model.PassengerCar;
import ru.job4j.ood.lcp.carparking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class BadAdmin {
    private List<Vehicle> pCars = new ArrayList<>();
    private List<Vehicle> trucks = new ArrayList<>();
    private PassengerCar passengerCar;

    public BadAdmin(List<Vehicle> pCars, List<Vehicle> trucks, PassengerCar passengerCar) {
        this.pCars = pCars;
        this.trucks = trucks;
        this.passengerCar = passengerCar;
    }

    private boolean add(List<Vehicle> list, PassengerCar passengerCar) {
        return false;
    }

    private void printRsl() {
        System.out.println("The car has been added");
    }
}

