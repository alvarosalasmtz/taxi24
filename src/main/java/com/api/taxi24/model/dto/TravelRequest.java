package com.api.taxi24.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class TravelRequest implements Serializable {

    private String driverId;
    private String passengerId;
    private double latitude;
    private double longitude;
}
