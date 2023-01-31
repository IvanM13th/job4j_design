package ru.job4j.ood.lcp.carparking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lcp.carparking.model.PassengerCar;
import ru.job4j.ood.lcp.carparking.model.Truck;
import ru.job4j.ood.lcp.carparking.parking.FirstParking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
public class AdministratorTest {
    @Test
    public void whenTruckSizeOneTheException() {
        assertThatThrownBy(() -> new Truck(000, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * В этом темте проверяем, что когда есть места, тогда расставляем машины
     */
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

    /**
     * Тест проверяет, что мы получим ошибку в случае,
     * когда все месте для траков уже заняты и на легковой парковке недостаточно мест.
     */
    @Test
    public void whenNotEnoughSpaceForTruckThenException() {
        Truck truck = new Truck(0000, 3);
        Truck anotherTruck = new Truck(0000, 3);
        Truck thirdTruck = new Truck(0000, 3);
        FirstParking firstParking = new FirstParking(2, 2);
        firstParking.add(truck);
        firstParking.add(anotherTruck);
        assertThatThrownBy(() -> firstParking.add(thirdTruck))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тест проверяет, что когда места на парковке для траков заняты,
     * мы можем разместить трак на парковке для легковых автомобилей,
     * при этом легковой автомобиль встать туда не сможет, если закончились места
     */
    @Test
    public void whenPlaceTruckToPassengerPlaces() {
        Truck truck = new Truck(0000, 2);
        Truck anotherTruck = new Truck(1111, 2);
        PassengerCar pCar = new PassengerCar(000);
        FirstParking firstParking = new FirstParking(2, 1);
        firstParking.add(truck);
        firstParking.add(anotherTruck);
        assertThat(firstParking.getTrucks()).contains(truck);
        assertThat(firstParking.getPassengerCars()).contains(anotherTruck);
        assertThatThrownBy(() -> firstParking.add(pCar))
                .isInstanceOf(IllegalArgumentException.class);

    }

    /**
     * Тест проверяет, что в случае, когда места для траков заняты
     * и на парковке для легковых нет мест, мы получим исключение
     */
    @Test
    public void whenPlaceTruckAndAnotherTruckThenException() {
        PassengerCar passengerCar = new PassengerCar(1111);
        PassengerCar anotherPassengerCar = new PassengerCar(2222);
        Truck truck = new Truck(0000, 2);
        Truck anotherTruck = new Truck(1111, 3);
        FirstParking firstParking = new FirstParking(4, 1);
        firstParking.add(passengerCar);
        firstParking.add(anotherPassengerCar);
        firstParking.add(truck);
        assertThatThrownBy(() -> firstParking.add(anotherTruck))
                .isInstanceOf(IllegalArgumentException.class);

    }

    /**
     * Тест проверяет, что в случае, когда заняты места для легковых,
     * но ест место для траков, при попытке поставить легковую мы получим исключение
     */
    @Test
    public void whenTryToPlacePCarToTruckPlace() {
        PassengerCar pCar = new PassengerCar(1111);
        PassengerCar anotherPCar = new PassengerCar(2222);
        FirstParking firstParking = new FirstParking(1, 15);
        firstParking.add(pCar);
        assertThatThrownBy(() -> firstParking.add(anotherPCar))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
