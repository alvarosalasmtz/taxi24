package com.api.taxi24.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Setter
@Getter
public class Passenger {

    @Id
    private String id;
    private String name;
    private LocalDateTime lastUpdate;
    private LocalDateTime dateCreate;
}
