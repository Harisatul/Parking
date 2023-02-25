package org.txtanon.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Sedan extends Car {
    private int sedanSize;

    public Sedan(String numberPlate,String color,
                 String manufacture, String year) {
        super(numberPlate, color, manufacture, year);
        this.sedanSize = 1;
    }
}
