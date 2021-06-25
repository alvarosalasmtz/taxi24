package com.api.taxi24.service;

import com.api.taxi24.model.dto.TravelRequest;
import com.api.taxi24.model.entity.Travel;
import com.api.taxi24.model.enums.TravelStatusEnum;

import java.util.List;

public interface TravelService {

    Travel findById(String id);

    List<Travel> findAll();

    List<Travel> findAllByStatus(TravelStatusEnum status);

    Travel create(TravelRequest request);

    Travel finish(String id, double latitude, double longitude);

    void deleteAll();
}
