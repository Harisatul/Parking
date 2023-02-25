package org.txtanon.repository;

import org.txtanon.entities.Car;

import java.util.ArrayList;
import java.util.List;

public class ParkRepositoryImplement implements ParkRepository {

    List<List<Car>> park;

    public ParkRepositoryImplement(int floor) {
        this.park = new ArrayList<>(floor);
        for (int i = 0; i < floor; i++) {
            this.park.add(new ArrayList<>(5));
        }
    }

    @Override
    public Car save(Car car, int floor) {
        if (floor == 1)
            floor = 0;
        List<Car> cars = park.get(floor);
        cars.add(car);
        return car;
    }

    @Override
    public void delete(String s) {

    }
}
