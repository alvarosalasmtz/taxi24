package com.api.taxi24.model.entity;

import com.api.taxi24.model.enums.DriverStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Setter
@Getter
public class Driver {

    @Id
    private String id;
    private String name;
    private GeoJsonPoint location;
    private DriverStatusEnum status;
    private LocalDateTime lastUpdate;
    private LocalDateTime dateCreate;
}
