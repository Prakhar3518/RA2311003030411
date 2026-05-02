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
@Slf4j //logger
public class LoggingService {

    private static final String LOG_API_URL = "http://20.207.122.201/evaluation-service/logs";
    private static final String AUTH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiYXVkIjoiaHR0cDovLzIwLjI0NC41Ni4xNDQvZXZhbHVhdGlvbi1zZXJ2aWNlIiwiZW1haWwiOiJwYzE5NzRAc3JtaXN0LmVkdS5pbiIsImV4cCI6MTc3NzY5ODg3MCwiaWF0IjoxNzc3Njk3OTcwLCJpc3MiOiJBZmZvcmQgTWVkaWNhbCBUZWNobm9sb2dpZXMgUHJpdmF0ZSBMaW1pdGVkIiwianRpIjoiYjM3ZWMzZTEtY2U4ZC00YThjLWI5ZWUtODRkY2M0NjM4ODdhIiwibG9jYWxlIjoiZW4tSU4iLCJuYW1lIjoicHJha2hhciBjaGF1ZGhhcnkiLCJzdWIiOiIyNzRkMTQxNC1hNGQ1LTRkYzktYTIzNS02YmMxMjkwM2I3MDcifSwiZW1haWwiOiJwYzE5NzRAc3JtaXN0LmVkdS5pbiIsIm5hbWUiOiJwcmFraGFyIGNoYXVkaGFyeSIsInJvbGxObyI6InJhMjMxMTAwMzAzMDQxMSIsImFjY2Vzc0NvZGUiOiJRa2JweEgiLCJjbGllbnRJRCI6IjI3NGQxNDE0LWE0ZDUtNGRjOS1hMjM1LTZiYzEyOTAzYjcwNyIsImNsaWVudFNlY3JldCI6ImpVSGtOeGp3SFFYYVB4TU4ifQ.yowsUzClFxpGs1dwAdHOF_ibmJ-eE0GYSF8xPN1z8vM";

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
            headers.setBearerAuth(AUTH_TOKEN);

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