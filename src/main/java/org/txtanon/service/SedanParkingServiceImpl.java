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
    public Car addCarToPark(Sedan car, int floor) throws Exception {
        Boolean carByPlateNumber = parkRepository.findCarByPlateNumber(car.getNumberPlate()).isPresent();
        if (carByPlateNumber) {
            log.error("Sedan with plate number {} has already in park",  car.getNumberPlate());
            throw new Exception(String.format(
                    "Sedan Service : Sedan with plate number %s has already in park", car.getNumberPlate()));
        }
        Car[] parkByFloor = parkRepository.getParkByFloor(floor);
        int length = parkByFloor.length;
        for (int i = 0; i < length; i++) {
            if (parkByFloor[i] == null) {
                return parkRepository.save(car, floor, i);
            }
        }
        throw new Exception("Sedan Service : park is full");
    }

}
