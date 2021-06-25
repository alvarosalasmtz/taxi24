package com.api.taxi24.service;

import com.api.taxi24.model.dto.DriverRequest;
import com.api.taxi24.model.entity.Driver;
import com.api.taxi24.model.enums.DriverStatusEnum;
import com.api.taxi24.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private MongoTemplate template;

    @Value("${geo.radio}")
    private double radioConstant;

    @PostConstruct
    private void init() {
        template.indexOps(Driver.class).ensureIndex(new GeospatialIndex("location")
                .typed(GeoSpatialIndexType.GEO_2DSPHERE));
    }

    @Override
    public Driver findById(String id) {
        Optional<Driver> response = driverRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        throw new RuntimeException("Not Found.");
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public List<Driver> findAllByStatus(DriverStatusEnum status) {
        return driverRepository.findByStatus(status);
    }

    @Override
    public List<Driver> findAllByLocationAndRadioAndStatus(
            double latitude, double longitude, int radio, DriverStatusEnum status) {
        return driverRepository.findDriversByLatitudeAndLongitudeAndRadioAndStatus(
                longitude, latitude, radio / radioConstant, status);
    }

    @Override
    public List<Driver> findAllByLocationNearAndStatus(double latitude, double longitude, DriverStatusEnum status) {
        return driverRepository.findDriversByLatitudeAndLongitudeAndStatus(longitude, latitude, status);
    }

    @Override
    public Driver create(DriverRequest request) {
        Driver driver = new Driver();
        driver.setId(UUID.randomUUID().toString());
        driver.setName(request.getName());
        driver.setLocation(new GeoJsonPoint(request.getLongitude(), request.getLatitude()));
        driver.setStatus(DriverStatusEnum.ONLINE);
        LocalDateTime now = LocalDateTime.now();
        driver.setDateCreate(now);
        driver.setLastUpdate(now);
        return driverRepository.save(driver);
    }

    @Override
    public void deleteAll() {
        driverRepository.deleteAll();
    }

    @Override
    public void updateLocationAndStatusById(String id, double latitude, double longitude, DriverStatusEnum status) {
        Driver driver = findById(id);
        driver.setLastUpdate(LocalDateTime.now());
        driver.setLocation(new GeoJsonPoint(longitude, latitude));
        driver.setStatus(status);
        driverRepository.save(driver);
    }
}
