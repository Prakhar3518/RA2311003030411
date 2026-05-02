package com.ra2311003030411.task.model;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScheduleResult {
    private Integer depotId;
    private Integer mechanicHours;
    private Integer totalDuration;
    private Integer totalImpact;
    private List<Vehicle> selectedVehicles;
}
