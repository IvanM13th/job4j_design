package ru.job4j.ood.lcp.carparking;

import ru.job4j.ood.lcp.carparking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class BadAdmin {
    private List<Vehicle> pCars = new ArrayList<>();
    private List<Vehicle> trucks = new ArrayList<>();
    private Vehicle vehicle;

    public BadAdmin(List<Vehicle> pCars, List<Vehicle> trucks, Vehicle vehicle) {
        this.pCars = pCars;
        this.trucks = trucks;
        this.vehicle = vehicle;
    }

    private boolean add(List<Vehicle> list, Vehicle vehicle) {
        return false;
    }

    private void printRsl() {
        System.out.println("The car has been added");
    }
}

