package org.txtanon.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.txtanon.entities.Car;
import org.txtanon.entities.Truck;
import org.txtanon.repository.ParkRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TruckParkServiceImplTest {

    @Mock
    ParkRepository<Truck, String> parkRepository;
    @InjectMocks
    TruckParkServiceImpl service;



    @Test
    void whenaddTruckToParkSuccess() throws Exception {

        Truck truck = new Truck();
        truck.setNumberPlate("BG 671 AS");
        truck.setColor("Blue");

        Truck truck2 = new Truck();
        truck2.setNumberPlate("BG 111 FS");
        truck2.setColor("Blue");

        Car[] carList = new Car[5];
        carList[0] = truck;
        carList[1] = truck;

        when(parkRepository.findCarByPlateNumber("BG 671 AS")).thenReturn(Optional.empty());
        when(parkRepository.getParkByFloor(any(Integer.class))).thenReturn(carList);
        when(parkRepository.save(any(Truck.class), any(Integer.class), any(Integer.class))).thenReturn(truck);

        Car savedTruck = service.addCarToPark(truck, 2);

        Assertions.assertEquals(truck.getNumberPlate(), savedTruck.getNumberPlate());

        verify(parkRepository, times(1)).findCarByPlateNumber(any(String.class));
        verify(parkRepository, times(2)).save(any(Truck.class),
                any(Integer.class), any(Integer.class));
    }

    @Test
    void deleteCarFromPark() {
    }
}