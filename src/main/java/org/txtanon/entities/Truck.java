package org.txtanon.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Truck extends Car{

    private int truckSize;

    public Truck(String numberPlate,String color,
                 String manufacture, String year) {
        super(numberPlate, color, manufacture, year);
        this.truckSize = 2;
    }

}
