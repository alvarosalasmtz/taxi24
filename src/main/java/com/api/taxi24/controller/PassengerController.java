package com.api.taxi24.controller;

import com.api.taxi24.model.dto.PassengerRequest;
import com.api.taxi24.model.entity.Passenger;
import com.api.taxi24.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/passengers")
@RestController
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> findById(@PathVariable String id) {
        return new ResponseEntity<>(passengerService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> findAll() {
        return new ResponseEntity<>(passengerService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Passenger> create(@RequestBody PassengerRequest request) {
        return new ResponseEntity<>(passengerService.create(request), HttpStatus.CREATED);
    }
}
