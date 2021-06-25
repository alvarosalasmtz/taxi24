package com.api.taxi24.service;

import com.api.taxi24.model.dto.PassengerRequest;
import com.api.taxi24.model.entity.Passenger;

import java.util.List;

public interface PassengerService {

    Passenger findById(String id);

    List<Passenger> findAll();

    Passenger create(PassengerRequest request);

    void deleteAll();
}
