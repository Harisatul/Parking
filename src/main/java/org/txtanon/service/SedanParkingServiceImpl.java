package org.txtanon.service;

import lombok.extern.slf4j.Slf4j;
import org.txtanon.entities.Car;
import org.txtanon.entities.Sedan;
import org.txtanon.repository.ParkRepository;


@Slf4j
public class SedanParkingServiceImpl implements ParkService {

    private final ParkRepository<Sedan, String> parkRepository;

    public SedanParkingServiceImpl(ParkRepository<Sedan, String> parkRepository) {
        this.parkRepository = parkRepository;
    }

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