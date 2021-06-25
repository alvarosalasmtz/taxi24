package com.api.taxi24.config;

import com.api.taxi24.model.dto.DriverDto;
import com.api.taxi24.model.dto.PassengerDto;
import com.api.taxi24.model.dto.TravelDto;
import com.api.taxi24.model.entity.Driver;
import com.api.taxi24.model.entity.Passenger;
import com.api.taxi24.service.DriverService;
import com.api.taxi24.service.PassengerService;
import com.api.taxi24.service.TravelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.api.taxi24.utils.JsonResourceUtils.getObjectByJsonFile;

@Slf4j
@Configuration
public class InitConf {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private TravelService travelService;

    @Bean
    public void createData() {
        log.info("Se crean Pasajeros...");
        passengerService.deleteAll();
        PassengerDto passengerDto = getObjectByJsonFile("passengers.json", PassengerDto.class);
        passengerDto.getList().forEach(passenger -> passengerService.create(passenger));
        log.info("Se crean Conductores...");
        driverService.deleteAll();
        DriverDto driverDto = getObjectByJsonFile("drivers.json", DriverDto.class);
        driverDto.getList().forEach(driver -> driverService.create(driver));
        log.info("Se crean Viajes...");
        travelService.deleteAll();
        TravelDto travelDto = getObjectByJsonFile("travels.json", TravelDto.class);
        List<Driver> drivers = driverService.findAll();
        List<Passenger> passengers = passengerService.findAll();
        travelDto.getList().get(0).setDriverId(drivers.get(0).getId());
        travelDto.getList().get(0).setPassengerId(passengers.get(0).getId());
        travelService.create(travelDto.getList().get(0));
        travelDto.getList().get(1).setDriverId(drivers.get(1).getId());
        travelDto.getList().get(1).setPassengerId(passengers.get(1).getId());
        travelService.create(travelDto.getList().get(1));
    }
}
