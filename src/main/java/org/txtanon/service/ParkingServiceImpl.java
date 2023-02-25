package org.txtanon.service;

import org.txtanon.entities.Car;
import org.txtanon.repository.ParkRepository;

public class ParkingServiceImpl implements ParkService {

    private final ParkRepository parkRepository;

    public ParkingServiceImpl(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }


    @Override
    public Car addCarToPark(Car car, int floor) {
        return parkRepository.save(car, floor);
    }
}
