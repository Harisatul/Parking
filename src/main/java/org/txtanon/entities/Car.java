package org.txtanon.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This base class. Represents a car with its number plate, color, manufacture, and year of production.
 * @author haris
 * @see Sedan
 * @see Truck
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    /**
     * The number plate of the car.
     */
    private String numberPlate;
    /**
     * The color of the car.
     */
    private String color;
    /**
     * The manufacture of the car.
     */
    private String manufacture;
    /**
     * The year of production of the car.
     */
    private String year;


}
