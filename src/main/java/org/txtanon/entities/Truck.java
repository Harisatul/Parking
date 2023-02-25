package org.txtanon.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Truck extends Car{

    private int truckSize;

    public Truck(String numberPlate,String color,
                 String manufacture, String year) {
        super(numberPlate, color, manufacture, year);
        this.truckSize = 2;
    }

}
