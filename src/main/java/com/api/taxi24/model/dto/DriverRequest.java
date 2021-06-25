package com.api.taxi24.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class DriverRequest implements Serializable {

    private String name;
    private double latitude;
    private double longitude;
}
