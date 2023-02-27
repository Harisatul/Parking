package org.txtanon.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.txtanon.entities.Car;
import org.txtanon.entities.Sedan;
import org.txtanon.repository.ParkRepository;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class SedanParkingServiceImplTest {

    @Mock
    ParkRepository<Sedan, String> parkRepository;
    @InjectMocks
    SedanParkingServiceImpl service;

    @Test
    void whenAddSedanToParkSuccess() throws Exception   {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 671 AS");
        sedan.setColor("Blue");

        Sedan sedan2 = new Sedan();
        sedan2.setNumberPlate("BG 111 FS");
        sedan2.setColor("Blue");

        Car[] carList = new Car[5];
        carList[0] = sedan2;
        carList[3] = sedan;

        when(parkRepository.findCarByPlateNumber("BG 671 AS")).thenReturn(Optional.empty());
        when(parkRepository.getParkByFloor(any(Integer.class))).thenReturn(carList);
        when(parkRepository.save(any(Sedan.class), any(Integer.class), any(Integer.class))).thenReturn(sedan);

        Car savedCar = service.addCarToPark(sedan, 2);

        System.out.println(sedan.getNumberPlate());
        System.out.println(savedCar.getNumberPlate());
        Assertions.assertEquals(sedan.getNumberPlate(), savedCar.getNumberPlate());

        verify(parkRepository, times(1)).findCarByPlateNumber(any(String.class));
        verify(parkRepository, times(1)).save(any(Sedan.class),
                any(Integer.class), any(Integer.class));
    }

    @Test
    void whenAddSedanToParkIsFull() {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 671 AS");
        sedan.setColor("Blue");

        Sedan sedan2 = new Sedan();
        sedan2.setNumberPlate("BG 111 FS");
        sedan2.setColor("Blue");

        // park is full
        Car[] carList = new Car[5];
        carList[0] = sedan2;
        carList[1] = sedan;
        carList[2] = sedan;
        carList[3] = sedan;
        carList[4] = sedan;

        when(parkRepository.findCarByPlateNumber("BG 671 AS")).thenReturn(Optional.empty());
        when(parkRepository.getParkByFloor(any(Integer.class))).thenReturn(carList);

        Exception exception = Assertions.assertThrows(Exception.class, () -> service.addCarToPark(sedan, 2));
        System.out.println(exception.getMessage());
        Assertions.assertEquals("park is full", exception.getMessage());


        verify(parkRepository, times(1)).findCarByPlateNumber(any(String.class));
        verify(parkRepository, times(0)).save(any(Sedan.class),
                any(Integer.class), any(Integer.class));
    }

    @Test
    void whenAddSedanToParkHasOccupied() {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 671 AS");
        sedan.setColor("Blue");

        Sedan sedan2 = new Sedan();
        sedan2.setNumberPlate("BG 111 FS");
        sedan2.setColor("Blue");

        // park is full
        Car[] carList = new Car[5];
        carList[0] = sedan2;
        carList[1] = sedan;
        carList[2] = sedan;
        carList[3] = sedan;
        carList[4] = sedan;

        when(parkRepository.findCarByPlateNumber("BG 671 AS")).thenReturn(Optional.of(sedan));

        Exception exception = Assertions.assertThrows(Exception.class, () -> service.addCarToPark(sedan, 2));
        System.out.println(exception.getMessage());
        String format = String.format(
                "Sedan with plate number %s has already in park", sedan.getNumberPlate());
        System.out.println(format);
        Assertions.assertEquals(format, exception.getMessage());


        verify(parkRepository, times(1)).findCarByPlateNumber(any(String.class));
        verify(parkRepository, times(0)).save(any(Sedan.class),
                any(Integer.class), any(Integer.class));
    }

    @Test
    void whenDeleteSedanFromParkSuccess() throws Exception   {
        Sedan sedan = new Sedan();
        sedan.setNumberPlate("BG 671 AS");
        sedan.setColor("Blue");

        Sedan sedan2 = new Sedan();
        sedan2.setNumberPlate("BG 111 FS");
        sedan2.setColor("Blue");


        when(parkRepository.delete("BG 671 AS")).thenReturn(Boolean.TRUE);


        Boolean aBoolean = service.deleteCarFromPark("BG 671 AS");

        Assertions.assertEquals(Boolean.TRUE, aBoolean);


        verify(parkRepository, times(1)).delete(any(String.class));
    }
}