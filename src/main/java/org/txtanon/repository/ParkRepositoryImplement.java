package org.txtanon.repository;

import org.txtanon.entities.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkRepositoryImplement implements ParkRepository {

    List<Car[]> park;

    public ParkRepositoryImplement(int floor) {
        this.park = new ArrayList<>(floor);
        for (int i = 0; i < floor; i++) {
            this.park.add(new Car[5]);
        }
    }

    @Override
    public Car save(Car car, int floor, int index) {
        if (floor == 1)
            floor = 0;
        Car[] cars = park.get(floor);
        cars[index] = car;
        return car;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public Optional<Car> findCarByPlateNumber(String numberPLate) {
        for (Car[] floor: park) {
            for (int i = 0; i < floor.length; i++) {
                if (floor[i].getNumberPlate().equals(numberPLate))
                    return Optional.of(floor[i]);
            }
        }
        return Optional.empty();
    }

    @Override
    public Car[] getParkByFloor(int floor) {
        if (floor == 1)
            floor = 0;
        return park.get(floor);
    }
}
