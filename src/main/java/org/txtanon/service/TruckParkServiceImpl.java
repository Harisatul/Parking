package org.txtanon.service;

import lombok.extern.slf4j.Slf4j;
import org.txtanon.entities.Car;
import org.txtanon.entities.Truck;
import org.txtanon.repository.ParkRepository;

@Slf4j
public class TruckParkServiceImpl implements ParkService {

    private final ParkRepository<Truck, String> parkRepository;

    public TruckParkServiceImpl(ParkRepository<Truck, String> parkRepository) {
        this.parkRepository = parkRepository;
    }

    @Override
    public Car addCarToPark(Car car, int floor) throws Exception {
        boolean carByPlateNumber = parkRepository.findCarByPlateNumber(car.getNumberPlate()).isPresent();
        if (carByPlateNumber) {
            log.error("Truck with plate number {} has already in park", car.getNumberPlate());
            throw new Exception(String.format(
                    "Truck with plate number %s has already in park", car.getNumberPlate()));
        }
        Car[] parkByFloor = parkRepository.getParkByFloor(floor);
        int length = parkByFloor.length;
        for (int i = 0; i < length-1; i++) {
            if (parkByFloor[i] == null && parkByFloor[i+1] == null) {
                Truck savedTruck = parkRepository.save((Truck) car, floor, i);
                parkRepository.save((Truck) car, floor, i+1);
                log.info("Truck with plate number {} sucessfully added to park", car.getNumberPlate());
                return savedTruck;
            }
        }
        log.error("park is full");
        throw new Exception("park is full");
    }

    @Override
    public Boolean deleteCarFromPark(String platNumber) throws Exception  {
        boolean flag = false;
        for (int i = 0; i < 2; i++) {
            Boolean delete = parkRepository.delete(platNumber);
            flag = delete;
        }
        if (flag){
            log.info("Truck with plate number {} has removed", platNumber);
            return flag;
        }
        log.error("Truck with plate number {} not found", platNumber);
        String exFormat = String.format(
                "Truck with plate number %s not found", platNumber);
        throw new Exception(exFormat);
    }
}
