package org.txtanon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String numberPlate;
    private String color;
    private String manufacture;
    private String year;

}
