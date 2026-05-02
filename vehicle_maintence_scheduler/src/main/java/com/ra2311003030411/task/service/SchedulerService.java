package com.ra2311003030411.task.service;


import com.ra2311003030411.task.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String baseUrl;

    public List<ScheduleResult> scheduleAll() {
        List<Depot> depots = fetchDepots();
        List<Vehicle> vehicles = fetchVehicles();

        List<ScheduleResult> results = new ArrayList<>();
        for (Depot depot : depots) {
            results.add(schedule(depot, vehicles));
        }
        return results;
    }

    private List<Depot> fetchDepots() {
        DepotResponse response = restTemplate.getForObject(baseUrl + "/evaluation-service/depots", DepotResponse.class);
        return response != null ? response.getDepots() : List.of();
    }

    private List<Vehicle> fetchVehicles() {
        VehicleResponse response = restTemplate.getForObject(baseUrl + "/evaluation-service/vehicles", VehicleResponse.class);
        return response != null ? response.getVehicles() : List.of();
    }

    private ScheduleResult schedule(Depot depot, List<Vehicle> vehicles) {
        int n = vehicles.size();
        int budget = depot.getMechanicHours();

        // 0/1 Knapsack DP
        int[][] dp = new int[n + 1][budget + 1];
        for (int i = 1; i <= n; i++) {
            int dur = vehicles.get(i - 1).getDuration();
            int imp = vehicles.get(i - 1).getImpact();
            for (int w = 0; w <= budget; w++) {
                dp[i][w] = dp[i - 1][w];
                if (dur <= w) {
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - dur] + imp);
                }
            }
        }

        // Backtrack to find selected vehicles
        List<Vehicle> selected = new ArrayList<>();
        int rem = budget;
        for (int i = n; i >= 1; i--) {
            if (dp[i][rem] != dp[i - 1][rem]) {
                selected.add(vehicles.get(i - 1));
                rem -= vehicles.get(i - 1).getDuration();
            }
        }

        int totalDuration = selected.stream().mapToInt(Vehicle::getDuration).sum();
        int totalImpact = selected.stream().mapToInt(Vehicle::getImpact).sum();

        return ScheduleResult.builder()
                .depotId(depot.getId())
                .mechanicHours(depot.getMechanicHours())
                .totalDuration(totalDuration)
                .totalImpact(totalImpact)
                .selectedVehicles(selected)
                .build();
    }
}