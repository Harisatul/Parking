package org.txtanon.service;

import org.txtanon.entities.Car;

public interface ParkService {

    Car addCarToPark(Car car, int floor) throws Exception;
    Boolean deleteCarFromPark(String platNumber) throws Exception;

}
