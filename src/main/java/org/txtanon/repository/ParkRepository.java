package org.txtanon.repository;

import org.txtanon.entities.Car;

public interface ParkRepository<T extends Car, NumberPlate extends String> {

    T save (T car, int floor);
    void delete (NumberPlate numberPlate);

}
