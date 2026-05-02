package com.middleware.logging.service;

import com.middleware.logging.model.LogConstants;
import com.middleware.logging.model.LogRequest;
import com.middleware.logging.model.LogResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j  // Logger
public class LoggingService {

    private static final String LOG_API_URL = "http://20.207.122.201/evaluation-service/logs";

    private final RestTemplate restTemplate;

    public void log(String stack, String level, String packageName, String message) {

        if (!LogConstants.VALID_STACKS.contains(stack) ||
                !LogConstants.VALID_LEVELS.contains(level) ||
                !isValidPackage(stack, packageName)) {

            log.warn("Invalid log fields: {}, {}, {}", stack, level, packageName);
            return;
        }

        try {
            LogRequest logRequest = new LogRequest(stack, level, packageName, message);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<LogRequest> request = new HttpEntity<>(logRequest, headers);

            restTemplate.exchange(LOG_API_URL, HttpMethod.POST, request, LogResponse.class);

        } catch (Exception e) {
            log.error("Failed to send log: {}", e.getMessage());
        }
    }

    private boolean isValidPackage(String stack, String packageName) {
        if (LogConstants.SHARED_PACKAGES.contains(packageName)) return true;
        if ("backend".equals(stack)) return LogConstants.BACKEND_PACKAGES.contains(packageName);
        if ("frontend".equals(stack)) return LogConstants.FRONTEND_PACKAGES.contains(packageName);
        return false;
    }
}