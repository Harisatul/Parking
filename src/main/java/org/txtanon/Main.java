package org.txtanon;

import org.txtanon.entities.Car;
import org.txtanon.repository.ParkRepository;
import org.txtanon.repository.ParkRepositoryImplement;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.setNumberPlate("BG 2017 AG");
        List<List<Car>> listOfCar = new ArrayList<>();
        ParkRepository<Car, String> parkRepository = new ParkRepositoryImplement(2);
        Car save = parkRepository.save(car, 0);
        System.out.println(save);

    }
}