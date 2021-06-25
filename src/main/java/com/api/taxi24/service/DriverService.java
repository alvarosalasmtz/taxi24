package com.api.taxi24.service;

import com.api.taxi24.model.dto.DriverRequest;
import com.api.taxi24.model.entity.Driver;
import com.api.taxi24.model.enums.DriverStatusEnum;

import java.util.List;

public interface DriverService {

    Driver findById(String id);

    List<Driver> findAll();

    List<Driver> findAllByStatus(DriverStatusEnum status);

    List<Driver> findAllByLocationAndRadioAndStatus(
            double latitude, double longitude, int radio, DriverStatusEnum status);

    List<Driver> findAllByLocationNearAndStatus(double latitude, double longitude, DriverStatusEnum status);

    Driver create(DriverRequest request);

    void deleteAll();

    void updateLocationAndStatusById(String id, double latitude, double longitude, DriverStatusEnum status);
}
