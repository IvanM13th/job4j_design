package ru.job4j.template;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ForTests {

    public static void main(String[] args) {
        int rsl1 = countByFilter(List.of(15, 15, 1, 3, 55));
        System.out.println(rsl1);
        int rsl2 = countByFilter(List.of(15, 15, 1, 3, 55), (a) -> a > 0);
        System.out.println(rsl2);
    }

    private static int countByFilter(List<Integer> list) {
        int count = 0;
        for (var i : list) {
            if (i > 10) {
                count++;
            }
        }
        return count;
    }

    private static int countByFilter(List<Integer> list, Predicate<Integer> predicate) {
        int count = 0;
        for (var i : list) {
            if (predicate.test(i)) {
                count++;
            }
        }
        return count;
    }

    public static void workWithCar() {
        Car car = new Car(123456);

        car.setNumber(654321);

        List<Car> cars = new ArrayList<>();

        cars.add(car);
    }

}
