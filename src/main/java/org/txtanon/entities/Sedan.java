package org.txtanon.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sedan extends Car {
    private int sedanSize;

    public Sedan(String numberPlate,String color,
                 String manufacture, String year) {
        super(numberPlate, color, manufacture, year);
        this.sedanSize = 1;
    }
}
