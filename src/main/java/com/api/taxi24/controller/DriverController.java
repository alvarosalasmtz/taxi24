package com.api.taxi24.controller;

import com.api.taxi24.model.dto.DriverRequest;
import com.api.taxi24.model.entity.Driver;
import com.api.taxi24.model.enums.DriverStatusEnum;
import com.api.taxi24.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/drivers")
@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/{id}")
    public ResponseEntity<Driver> findById(@PathVariable String id) {
        return new ResponseEntity<>(driverService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> findAll() {
        return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Driver>> findByStatus(@PathVariable DriverStatusEnum status) {
        return new ResponseEntity<>(driverService.findAllByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/online/radio/{radio}/location")
    public ResponseEntity<List<Driver>> findAllOnlineAndLocation(
            @PathVariable int radio, @RequestParam double latitude, @RequestParam double longitude) {
        return new ResponseEntity<>(driverService.findAllByLocationAndRadioAndStatus(
                latitude, longitude, radio, DriverStatusEnum.ONLINE), HttpStatus.OK);
    }

    @GetMapping("/online/location-near")
    public ResponseEntity<List<Driver>> findAllOnlineAndLocationNear(
            @RequestParam double latitude, @RequestParam double longitude) {
        return new ResponseEntity<>(driverService.findAllByLocationNearAndStatus(
                latitude, longitude, DriverStatusEnum.ONLINE), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Driver> create(@RequestBody DriverRequest request) {
        return new ResponseEntity<>(driverService.create(request), HttpStatus.CREATED);
    }
}
