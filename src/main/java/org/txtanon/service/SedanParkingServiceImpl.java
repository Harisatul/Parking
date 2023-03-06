package org.txtanon.service;

import lombok.extern.slf4j.Slf4j;
import org.txtanon.entities.Car;
import org.txtanon.entities.Sedan;
import org.txtanon.repository.ParkRepository;


/**
 * Implementation of ParkService interface for Sedan cars.
 * @author haris
 */
@Slf4j
public class SedanParkingServiceImpl implements ParkService {

    private final ParkRepository<Sedan, String> parkRepository;

    /**
     * Constructor to set parkRepository
     * @param parkRepository Repository to manage park
     */
    public SedanParkingServiceImpl(ParkRepository<Sedan, String> parkRepository) {
        this.parkRepository = parkRepository;
    }


    /**
     * Add Sedan car to the park
     * The idea is, iterate through floor and specified floor to find empty space. if empty space was found
     * we assign that space with new Object of Sedan with calling parkRepository.save() and passing required value.
     * There is check if the parking lot has already same Car that we want to add.
     * Then, check if park is full. return exception
     *
     * @param car Sedan car to add
     * @param floor floor in which to park the car
     * @return added Sedan car
     * @throws Exception if Sedan car is already in park or park is full
     */
    @Override
    public Car addCarToPark(Car car, int floor) throws Exception {
        boolean carByPlateNumber = parkRepository.findCarByPlateNumber(car.getNumberPlate()).isPresent();
        if (carByPlateNumber) {
            log.error("Sedan with plate number {} has already in park", car.getNumberPlate());
            throw new Exception(String.format(
                    "Sedan with plate number %s has already in park", car.getNumberPlate()));
        }
        Car[] parkByFloor = parkRepository.getParkByFloor(floor);
        int length = parkByFloor.length;
        for (int i = 0; i < length; i++) {
            if (parkByFloor[i] == null) {
                log.info("Sedan with plate number {} sucessfully added to park", car.getNumberPlate());
                return parkRepository.save((Sedan) car, floor, i);
            }
        }
        log.error("park is full");
        throw new Exception("park is full");
    }

    /**
     * Delete Sedan car from the park
     * the idea is to call and pass the value to parkRepository.delete(platNumber)
     *
     * @param platNumber number plate of Sedan car to delete
     * @return true if Sedan car is successfully deleted, false if Sedan car is not found in the park
     * @throws Exception if Sedan car is not found in the park
     *
     */
    @Override
    public Boolean deleteCarFromPark(String platNumber) throws Exception {
        if (parkRepository.delete(platNumber)){
            log.info("Sedan with plate number {} successfully out", platNumber);
            return true;
        }
        log.error("Sedan with plate number {} not found", platNumber);
        String exFormat = String.format(
                "Sedan with plate number %s not found", platNumber);
        throw new Exception(exFormat);
    }
}