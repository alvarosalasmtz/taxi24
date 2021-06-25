package com.api.taxi24.repository;

import com.api.taxi24.model.entity.Driver;
import com.api.taxi24.model.enums.DriverStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {

    List<Driver> findByStatus(DriverStatusEnum status);

    @Query("{location: {$geoWithin: { $centerSphere: [ [ ?0, ?1 ], ?2 ]}}, status: ?3}")
    List<Driver> findDriversByLatitudeAndLongitudeAndRadioAndStatus(Double longitude, Double latitude,
                                                                    Double radio, DriverStatusEnum status);

    @Query(value = "{location: {$nearSphere: {$geometry: {type : \"Point\",coordinates : [ ?0, ?1]}}}, status: ?2}",
            sort = "{location: 1}")
    List<Driver> findDriversByLatitudeAndLongitudeAndStatus(Double longitude, Double latitude, DriverStatusEnum status);
}
