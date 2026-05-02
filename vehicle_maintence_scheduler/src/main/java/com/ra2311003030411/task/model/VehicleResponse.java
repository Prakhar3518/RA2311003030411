package com.ra2311003030411.task.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class VehicleResponse {
    @JsonProperty("vehicles")
    private List<Vehicle> vehicles;
}