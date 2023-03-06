package org.txtanon.repository;

import org.txtanon.entities.Car;

import java.util.Optional;

/**
 * This interface represents a parking repository that manages the parking slots for cars.
 *
 * @param <T> The type of the car to be parked.
 * @param <NumberPlate> The type of the number plate for the car to be parked.
 * @author haris
 */
public interface ParkRepository<T extends Car, NumberPlate extends String> {

    /**
     * Saves the car object to the parking slot at the specified floor and index.
     *
     * @param car The car object to be saved.
     * @param floor The floor number of the parking slot.
     * @param index The index number of the floor.
     * @return The saved car object.
     */
    T save (T car, int floor, int index);

    /**
     * Deletes the car object from the parking slot with the specified number plate.
     *
     * @param numberPlate The number plate of the car to be deleted.
     * @return A boolean indicating whether the car was successfully deleted or not.
     */
    Boolean delete (NumberPlate numberPlate);

    /**
     * Finds the car object with the specified number plate.
     *
     * @param numberPlate The number plate of the car to be found.
     * @return An Optional containing the car object if found, otherwise an empty Optional.
     */
    Optional<T> findCarByPlateNumber(NumberPlate numberPlate);

    /**
     * Returns an array of all cars parked on the specified floor.
     *
     * @param floor The floor number for which the parked cars are to be returned.
     * @return An array of Car objects parked on the specified floor.
     */
    Car[] getParkByFloor(int floor);

}
