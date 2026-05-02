package com.middleware.logging.middleware;

import com.middleware.logging.service.LoggingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestLoggingMiddleware implements HandlerInterceptor {

    private final LoggingService loggingService;

    public RequestLoggingMiddleware(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        loggingService.log(
                "backend", "info", "middleware",
                "Incoming request: " + request.getMethod() + " " + request.getRequestURI()
        );
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        if (ex != null) {
            loggingService.log(
                    "backend", "error", "middleware",
                    "Request failed: " + request.getMethod() + " " + request.getRequestURI() + " error=" + ex.getMessage()
            );
        }
    }
}