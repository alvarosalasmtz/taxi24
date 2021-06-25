package com.api.taxi24.repository;

import com.api.taxi24.model.entity.Travel;
import com.api.taxi24.model.enums.TravelStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends MongoRepository<Travel, String> {

    List<Travel> findByStatus(TravelStatusEnum status);
}
