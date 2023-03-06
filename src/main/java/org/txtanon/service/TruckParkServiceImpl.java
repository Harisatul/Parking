package org.txtanon.service;

import lombok.extern.slf4j.Slf4j;
import org.txtanon.entities.Car;
import org.txtanon.entities.Truck;
import org.txtanon.repository.ParkRepository;

/**
 * Implementation of {@link ParkService} for managing truck parking function.
 */
@Slf4j
public class TruckParkServiceImpl implements ParkService {

    private final ParkRepository<Truck, String> parkRepository;

    /**
     * Constructs a new TruckParkServiceImpl with the specified repository.
     *
     * @param parkRepository the repository to use for managing truck parking
     */
    public TruckParkServiceImpl(ParkRepository<Truck, String> parkRepository) {
        this.parkRepository = parkRepository;
    }


    /**
     * Adds a truck to the parking lot on the specified floor.
     * The idea is, iterate through floor and specified floor to find 2-index empty space.
     *
     * example 1 = {sedan, null, null, sedan, sedan} ==> floor2
     *            when iterate over array, index 1 is null. Then we also check next index[i+1].
     *            if the next index is also empty, we can assign new Truck Object to the parking lot
     *            on that floor at index i and index i+1. so basically we put same truck object to two index respectively.
     * example 2 = {sedan, null, sedan, sedan, null} ==> floor2
     *            when iterate over array, index- 1 is null. Then we also check next index[i+1].
     *            in this case next index of i [i+1] isn't null. so we can't add new truck object since Truck object has
     *            2 size. Even though there is empty space on last index. we can't still add cause we should put Truck object
     *            respectively.
     *
     * if empty space was found
     * we assign that space with new Object of Truck with calling parkRepository.save() and passing required value.
     * There is check if the parking lot has already same Car that we want to add.
     * Then, check if park is full. return exception
     *
     *
     * @param car the truck to add to the parking lot
     * @param floor the floor to park the truck on
     * @return the truck that was added to the parking lot
     * @throws Exception if the parking lot is full or a truck with the same number plate is already parked
     *
     */
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

    /**
     * Removes a truck from the parking lot with the specified number plate.
     *
     * Since there are two object of truck (cause add truck functionality). We can iterate
     * two times and call parkRepository.delete(platNumber) twice. Return true if operation of delete success.
     * Otherwise, throw exception cause there are nothing Truck object with given platNumber
     *
     *
     * @param platNumber the number plate of the truck to remove
     * @return true if the truck was removed, false if no such truck was parked
     * @throws Exception if an error occurs while attempting to remove the truck
     */
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
