package com.middleware.logging.model;

import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class LogConstants {


    public final Set<String> VALID_STACKS = Set.of(
            "backend",
            "frontend"
    );

    // Valid Level values
    public final Set<String> VALID_LEVELS = Set.of(
            "debug",
            "info",
            "warn",
            "error",
            "fatal"
    );


    public final Set<String> BACKEND_PACKAGES = Set.of(
            "cache",
            "controller",
            "cron_job",
            "db",
            "domain",
            "handler",
            "repository",
            "route",
            "service"
    );

    // Valid Package values for Frontend
    public final Set<String> FRONTEND_PACKAGES = Set.of(
            "api",
            "component",
            "hook",
            "page",
            "state",
            "style"
    );

    // Valid Package values for fullstack
    public final Set<String> SHARED_PACKAGES = Set.of(
            "auth",
            "com/middleware/logging/config",
            "middleware",
            "utils"
    );
}