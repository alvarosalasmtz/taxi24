package com.api.taxi24.service;

import com.api.taxi24.model.dto.PassengerRequest;
import com.api.taxi24.model.entity.Passenger;
import com.api.taxi24.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;


    @Override
    public Passenger findById(String id) {
        Optional<Passenger> response = passengerRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        throw new RuntimeException("Not Found.");
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger create(PassengerRequest request) {
        Passenger passenger = new Passenger();
        passenger.setId(UUID.randomUUID().toString());
        passenger.setName(request.getName());
        LocalDateTime now = LocalDateTime.now();
        passenger.setDateCreate(now);
        passenger.setLastUpdate(now);
        return passengerRepository.save(passenger);
    }

    @Override
    public void deleteAll() {
        passengerRepository.deleteAll();
    }
}
