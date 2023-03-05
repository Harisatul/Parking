package org.txtanon;

import org.junit.jupiter.api.Assertions;
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
        * [Sedan, Truck, Truck, Sedan, Sedan] == floor 2
        * */


        System.out.println(savedSedan.getNumberPlate());
        System.out.println(savedTruck.getNumberPlate());
    }

    @Test
    void integrationWhenAddedCarFailed() throws Exception {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 223 SS");

        Truck truck = new Truck();
        truck.setNumberPlate("BG 543 S");

        // Added To Floor 1
        Car savedSedan = sedanParkingService.addCarToPark(sedan, 1);
        Car savedTruck = truckParkService.addCarToPark(truck, 1);

        // =====================================================================//

        Sedan sedan2 = new Sedan();
        sedan2.setNumberPlate("B 532 SD");

        Sedan sedan3 = new Sedan();
        sedan3.setNumberPlate("B 7411 RT");

        Truck truck2 = new Truck();
        truck2.setNumberPlate("BG 541 SR");

        // Added To Floor 2
        Car savedSedan2 = sedanParkingService.addCarToPark(sedan2, 2);
        Car savedSedan3 = sedanParkingService.addCarToPark(sedan3, 2);
        Car savedTruck2 = truckParkService.addCarToPark(truck2, 2);
        /*
         * so, we'll have something like this :
         * [sedan, truck, truck, null, null] == floor 1
         * [sedan2, sedan3, truck2, truck2, null] == floor 2
         * */

        // let's say we want to add more Truck on floor two
        Truck truck3 = new Truck();
        truck3.setNumberPlate("BG 643 S");

        // this operation gonna thrown error since no more space in floor 2
        Assertions.assertThrows(Exception.class, ()->{
            truckParkService.addCarToPark(truck3, 2);

        });
        System.out.println(savedSedan.getNumberPlate());
        System.out.println(savedTruck.getNumberPlate());
    }

    @Test
    void integrationWhenAddedTruckFailedButStillAbleAddSedan() throws Exception {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 223 SS");

        Truck truck = new Truck();
        truck.setNumberPlate("BG 543 S");

        // Added To Floor 1
        Car savedSedan = sedanParkingService.addCarToPark(sedan, 1);
        Car savedTruck = truckParkService.addCarToPark(truck, 1);

        // =====================================================================//

        Sedan sedan2 = new Sedan();
        sedan2.setNumberPlate("B 532 SD");

        Sedan sedan3 = new Sedan();
        sedan3.setNumberPlate("B 7411 RT");

        Truck truck2 = new Truck();
        truck2.setNumberPlate("BG 541 SR");

        // Added To Floor 2
        Car savedSedan2 = sedanParkingService.addCarToPark(sedan2, 2);
        Car savedSedan3 = sedanParkingService.addCarToPark(sedan3, 2);
        Car savedTruck2 = truckParkService.addCarToPark(truck2, 2);
        /*
         * so, we'll have something like this :
         * [sedan, truck, truck, null, null] == floor 1
         * [sedan2, sedan3, truck2, truck2, null] == floor 2
         * */

        // let's say we want to add more Truck on floor two
        Truck truck3 = new Truck();
        truck3.setNumberPlate("BG 643 S");

        // this operation gonna thrown error since no more space in floor 2
        Assertions.assertThrows(Exception.class, ()->{
            truckParkService.addCarToPark(truck3, 2);

        });

        // but, we can still add sedan on floor two
        Sedan sedan4 = new Sedan();
        sedan3.setNumberPlate("B 823 ST");
        Car car = sedanParkingService.addCarToPark(sedan4, 2);

        Assertions.assertEquals(sedan4.getNumberPlate(), car.getNumberPlate());

    }




}
