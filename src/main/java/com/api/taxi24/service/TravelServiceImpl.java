package com.api.taxi24.service;

import com.api.taxi24.model.dto.TravelRequest;
import com.api.taxi24.model.entity.Travel;
import com.api.taxi24.model.enums.DriverStatusEnum;
import com.api.taxi24.model.enums.TravelStatusEnum;
import com.api.taxi24.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private DriverService driverService;

    @Override
    public Travel findById(String id) {
        Optional<Travel> response = travelRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        throw new RuntimeException("Not Found.");
    }

    @Override
    public List<Travel> findAll() {
        return travelRepository.findAll();
    }

    @Override
    public List<Travel> findAllByStatus(TravelStatusEnum status) {
        return travelRepository.findByStatus(status);
    }

    @Override
    public Travel create(TravelRequest request) {
        Travel travel = new Travel();
        travel.setId(UUID.randomUUID().toString());
        travel.setDriverId(request.getDriverId());
        travel.setPassengerId(request.getPassengerId());
        travel.setLocationInit(new GeoJsonPoint(request.getLongitude(), request.getLatitude()));
        travel.setStatus(TravelStatusEnum.TRAVELING);
        LocalDateTime now = LocalDateTime.now();
        travel.setDateCreate(now);
        travel.setLastUpdate(now);
        driverService.updateLocationAndStatusById(
                request.getDriverId(), request.getLatitude(), request.getLongitude(), DriverStatusEnum.BUSY);
        return travelRepository.save(travel);
    }

    @Override
    public Travel finish(String id, double latitude, double longitude) {
        Travel travel = findById(id);
        travel.setStatus(TravelStatusEnum.FINISH);
        travel.setLocationFinish(new GeoJsonPoint(longitude, latitude));
        travel.setLastUpdate(LocalDateTime.now());
        driverService.updateLocationAndStatusById(
                travel.getDriverId(), latitude, longitude, DriverStatusEnum.ONLINE);
        return travelRepository.save(travel);
    }

    @Override
    public void deleteAll() {
        travelRepository.deleteAll();
    }
}
