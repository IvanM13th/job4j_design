package ru.job4j.ood.lcp.carparking;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lcp.carparking.model.PassengerCar;
import ru.job4j.ood.lcp.carparking.model.Truck;
import ru.job4j.ood.lcp.carparking.parking.FirstParking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AdministratorTest {
    @Test
    public void whenTruckSizeOneTheException() {
        assertThatThrownBy(() -> new Truck(000, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenEnoughPlacesThenPlaceVehicles() {
        PassengerCar passengerCar = new PassengerCar(1111);
        Truck truck = new Truck(0000, 2);
        FirstParking firstParking = new FirstParking(3, 3);
        firstParking.add(passengerCar);
        firstParking.add(truck);
        assertThat(firstParking.getPassengerCars()).contains(passengerCar);
        assertThat(firstParking.getTrucks()).contains(truck);
    }

    @Test
    public void whenNotEnoughSpaceForTruckThenException() {
        Truck truck = new Truck(0000, 3);
        FirstParking firstParking = new FirstParking(2, 2);
        firstParking.add(truck);
        assertThatThrownBy(() -> firstParking.add(truck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenPlaceTruckToPassengerPlaces() {
        Truck truck = new Truck(0000, 2);
        Truck anotherTruck = new Truck(1111, 2);
        FirstParking firstParking = new FirstParking(2, 2);
        firstParking.add(truck);
        firstParking.add(anotherTruck);
        assertThat(firstParking.getTrucks()).contains(truck);
        assertThat(firstParking.getPassengerCars()).contains(anotherTruck);

    }

    @Test
    public void whenPlaceTruckAndAnotherTruckThenException() {
        PassengerCar passengerCar = new PassengerCar(1111);
        PassengerCar anotherPassengerCar = new PassengerCar(2222);
        Truck truck = new Truck(0000, 2);
        Truck anotherTruck = new Truck(1111, 3);
        FirstParking firstParking = new FirstParking(4, 2);
        firstParking.add(passengerCar);
        firstParking.add(anotherPassengerCar);
        firstParking.add(truck);
        assertThatThrownBy(() -> firstParking.add(anotherTruck))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
