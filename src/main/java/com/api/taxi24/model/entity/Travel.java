package com.api.taxi24.model.entity;

import com.api.taxi24.model.enums.TravelStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Setter
@Getter
public class Travel {

    @Id
    private String id;
    private String driverId;
    private String passengerId;
    private GeoJsonPoint locationInit;
    private GeoJsonPoint locationFinish;
    private TravelStatusEnum status;
    private LocalDateTime lastUpdate;
    private LocalDateTime dateCreate;
}
