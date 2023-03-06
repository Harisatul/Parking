package org.txtanon.repository;

import org.txtanon.entities.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the ParkRepository interface and provides the functionality to manage
 * the parking slots for cars.
 * @see ParkRepository
 * @author haris
 */
public class ParkRepositoryImplement implements ParkRepository {

    /**
     * I think more convinient to use List of array implementation. since we have flexibility
     * to access index (position of car)
    **/
    private List<Car[]> park;

    /**
     * Constructs a new ParkRepositoryImplement object with the specified number of floors.
     * @param floor The number of floors in the parking lot that want to be specified.
     * example : new ParkRepsitoryImplement(2) -> this gonna make a parking lot with 2 floor
     *              with each floor has five capacity
     */
    public ParkRepositoryImplement(int floor) {
        this.park = new ArrayList<>(floor);
        for (int i = 0; i < floor; i++) {
            this.park.add(new Car[5]);
        }
    }


    /**
     * Saves the car object to the parking slot at the specified floor and index.
     * The idea is to get floor from park List than assign to index given.
     *
     * @param car The car object to be saved.
     * @param floor The floor number of the parking slot.
     * @param index The index number of the floor.
     * @return The saved car object.
     *
     * example : ParkRepositoryImplement.save(new Car(), 1, 1)
     * - > this gonna save a car on the floor 1 and index 1
     *
     */
    @Override
    public Car save(Car car, int floor, int index) {
        floor -= 1;
        Car[] cars = park.get(floor);
        cars[index] = car;
        return car;
    }

    /**
     * Deletes the car object from the parking slot with the specified number plate.
     * The idea is to iterate through park List and assign the index with null value
     * then return true if match on given plateNumber
     *
     * @param platNumber The number plate of the car to be deleted.
     * @return A boolean indicating whether the car was successfully deleted or not.
     */
    @Override
    public Boolean delete(String platNumber) {
        for (Car[] floor : park) {
            for (int i = 0; i < floor.length; i++) {
                // avoid error when check null value in the index
                if (floor[i] == null)
                    continue;
                if (floor[i].getNumberPlate().equals(platNumber)) {
                    floor[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Finds the car object with the specified number plate.
     * the idea is similar like delete method. However, return Optional of car object if found.
     * Otherwise, return optional of empty
     *
     * @param numberPLate The number plate of the car to be found.
     * @return An Optional containing the car object if found, otherwise an empty Optional.
     */
    @Override
    public Optional<Car> findCarByPlateNumber(String numberPLate) {
        for (Car[] floor: park) {
            for (int i = 0; i < floor.length; i++) {
                if (floor[i] == null)
                    continue;
                if (floor[i].getNumberPlate().equals(numberPLate))
                    return Optional.of(floor[i]);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns an array of all cars parked on the specified floor.
     * The idea of this method implementation pretty straightfordward. Get a floor by given value
     * retrieve from park List with given index.
     *
     * @param floor The floor number for which the parked cars are to be returned.
     * @return An array of Car objects parked on the specified floor.
     */
    @Override
    public Car[] getParkByFloor(int floor) {
        floor -= 1;
        return park.get(floor);
    }
}
