package org.txtanon.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.txtanon.entities.Car;
import org.txtanon.entities.Sedan;

class ParkRepositoryImplementTest {

    private ParkRepository<Car, String> parkRepository ;

    @BeforeEach
    void setUp() {
        parkRepository = new ParkRepositoryImplement(2);
    }

    @Test
    void saveSedanToParkSuccess() {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 671 AS");
        sedan.setColor("Blue");
        sedan.setManufacture("Honda");
        sedan.setYear("2017");
        Car save = parkRepository.save(sedan, 0, 1);
        Assertions.assertEquals(sedan.getNumberPlate(), save.getNumberPlate());
    }

    @Test
    void delete() {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 671 AS");
        sedan.setColor("Blue");
        Car save = parkRepository.save(sedan, 0, 1);
        Boolean delete = parkRepository.delete("BG 671 AS");
        System.out.println(delete);
        Assertions.assertEquals(save.getNumberPlate(), "BG 671 AS");
        Assertions.assertEquals(Boolean.TRUE, delete);
    }

    @Test
    void findCarByPlateNumber() {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 671 AS");
        sedan.setColor("Blue");
        Car carSaved = parkRepository.save(sedan, 1, 1);
        Car carRetrieved = parkRepository.findCarByPlateNumber("BG 671 AS").get();
        System.out.println("carRetrieved plate Number : "+carRetrieved.getNumberPlate());
        System.out.println("carSaved plate Number : " +carSaved.getNumberPlate());
        Assertions.assertEquals(carSaved.getNumberPlate(), "BG 671 AS");
    }
}