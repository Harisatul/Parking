package org.txtanon.repository;

import org.txtanon.entities.Car;

import java.util.Optional;

public interface ParkRepository<T extends Car, NumberPlate extends String> {

    T save (T car, int floor, int index);
    void delete (NumberPlate numberPlate);
    Optional<T> findCarByPlateNumber(NumberPlate numberPlate);
    Car[] getParkByFloor(int floor);

}
