package org.txtanon.service;

import org.txtanon.entities.Car;
import org.txtanon.entities.Sedan;

public interface ParkService {

    Car addCarToPark(Sedan car, int floor) throws Exception;

}
