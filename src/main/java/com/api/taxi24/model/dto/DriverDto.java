package com.api.taxi24.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DriverDto {
    private List<DriverRequest> list;
}
