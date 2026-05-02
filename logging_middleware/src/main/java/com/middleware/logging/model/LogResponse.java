package com.middleware.logging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogResponse {
    @JsonProperty("logID")
    private String logId;

    @JsonProperty("message")
    private String message;

    public LogResponse() {}

}
