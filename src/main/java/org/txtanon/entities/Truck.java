package org.txtanon.entities;

import lombok.*;


/**
 * Represents a Truck which is a subclass of Car with an additional attribute for the truck size.
 * @see Car
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Truck extends Car{

    /**
     * The size of the Truck.
     * Default value is 2.
     */
    private int truckSize = 2;

    /**
     * Initializes a new instance of the Truck class with the specified values for the fields.
     *
     * @param numberPlate The number plate of the Truck.
     * @param color The color of the Truck.
     * @param manufacture The manufacture of the Truck.
     * @param year The year of production of the Truck.
     */
    public Truck(String numberPlate,String color,
                 String manufacture, String year) {
        super(numberPlate, color, manufacture, year);
    }

}
