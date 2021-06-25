package com.api.taxi24.controller;

import com.api.taxi24.model.dto.TravelFinishRequest;
import com.api.taxi24.model.dto.TravelRequest;
import com.api.taxi24.model.entity.Travel;
import com.api.taxi24.model.enums.TravelStatusEnum;
import com.api.taxi24.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/travels")
@RestController
public class TravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping("/{id}")
    public ResponseEntity<Travel> findById(@PathVariable String id) {
        return new ResponseEntity<>(travelService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Travel>> findAll() {
        return new ResponseEntity<>(travelService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Travel>> findByStatus(@PathVariable TravelStatusEnum status) {
        return new ResponseEntity<>(travelService.findAllByStatus(status), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Travel> create(@RequestBody TravelRequest request) {
        return new ResponseEntity<>(travelService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Travel> finishTravel(@PathVariable String id, @RequestBody TravelFinishRequest request) {
        return new ResponseEntity<>(travelService.finish(
                id, request.getLatitude(), request.getLongitude()), HttpStatus.CREATED);
    }
}
