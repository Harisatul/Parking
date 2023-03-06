package org.txtanon.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Represents a Sedan car which is a subclass of Car with an additional attribute for the sedan size.
 * @see Car
 * @author haris
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sedan extends Car {
    /**
     * The size of the Sedan.
     * Default value is 1.
     */
    private int sedanSize = 1;
    /**
     * Initializes a new instance of the Sedan class with the specified values for the fields.
     *
     * @param numberPlate The number plate of the Sedan.
     * @param color The color of the Sedan.
     * @param manufacture The manufacture of the Sedan.
     * @param year The year of production of the Sedan.
     */
    public Sedan(String numberPlate,String color,
                 String manufacture, String year) {
        super(numberPlate, color, manufacture, year);
    }
}
