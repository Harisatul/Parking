package org.txtanon.service;

import org.txtanon.entities.Car;

/**

 The ParkService interface defines the methods for managing the parking lot.

 It allows adding a car to a specific floor of the parking lot and deleting a car from the parking lot.

 Implementation class :
 {@link SedanParkingServiceImpl}
 {@link TruckParkServiceImpl}

 @author haris
  */
public interface ParkService {


    /**
         Adds a car to the parking lot on the specified floor.

         @param car the car object to be added to the parking lot
         @param floor the floor where the car should be parked
         @return the car object that has been parked in the parking lot
         @throws Exception if the parking lot is full.
     */
    Car addCarToPark(Car car, int floor) throws Exception;
    /**
     Delete a car to the parking lot on the specified floor.

     @param platNumber the car object to be added to the parking lot
     @return the car object that has been parked in the parking lot
     @throws Exception if the parking lot is full.
     */
    Boolean deleteCarFromPark(String platNumber) throws Exception;

}
