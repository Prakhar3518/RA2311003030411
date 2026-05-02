package com.ra2311003030411.task.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Vehicle {
    @JsonProperty("TaskID")
    private String taskId;

    @JsonProperty("Duration")
    private Integer duration;

    @JsonProperty("Impact")
    private Integer impact;
}