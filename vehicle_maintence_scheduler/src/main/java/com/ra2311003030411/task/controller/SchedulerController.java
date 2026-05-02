package com.ra2311003030411.task.controller;

import com.ra2311003030411.task.model.ScheduleResult;
import com.ra2311003030411.task.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class SchedulerController {

    private final SchedulerService schedulerService;

    @GetMapping
    public ResponseEntity<List<ScheduleResult>> getSchedule() {
        return ResponseEntity.ok(schedulerService.scheduleAll());
    }
}