package org.txtanon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.txtanon.entities.Car;
import org.txtanon.entities.Sedan;
import org.txtanon.entities.Truck;
import org.txtanon.repository.ParkRepository;
import org.txtanon.repository.ParkRepositoryImplement;
import org.txtanon.service.SedanParkingServiceImpl;
import org.txtanon.service.TruckParkServiceImpl;

public class IntegrationTest {

    private ParkRepository parkRepository;
    private SedanParkingServiceImpl sedanParkingService;
    private TruckParkServiceImpl truckParkService;
    @BeforeEach
    void setUp() {
        this.parkRepository = new ParkRepositoryImplement(2);
        this.sedanParkingService = new SedanParkingServiceImpl(parkRepository);
        this.truckParkService= new TruckParkServiceImpl(parkRepository);

    }

    @Test
    void integrationWhenAddedThreeSedanAndOneTruckSuccess() throws Exception {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 223 SS");

        Truck truck = new Truck();
        truck.setNumberPlate("BG 543 S");

        // Added To Floor 2
        Car savedSedan = sedanParkingService.addCarToPark(sedan, 2);
        Car savedTruck = truckParkService.addCarToPark(truck, 2);

        Sedan sedan2 = new Sedan();
        sedan2.setNumberPlate("B 532 SD");

        Sedan sedan3 = new Sedan();
        sedan2.setNumberPlate("B 7411 RT");

        // Added To Floor 2
        Car savedSedan2 = sedanParkingService.addCarToPark(sedan2, 2);
        Car savedSedan3 = sedanParkingService.addCarToPark(sedan3, 2);

        /*
        * so, now on floor two. The parking lot has occupied
        * [null, null, null, null, null] == floor 1
        * [Sedan, Sedan, Truck, Sedan, Sedan] == floor 2
        * */


        System.out.println(savedSedan.getNumberPlate());
        System.out.println(savedTruck.getNumberPlate());
    }
}
