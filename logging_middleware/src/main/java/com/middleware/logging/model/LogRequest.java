package com.middleware.logging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // to generate getters, setters
@NoArgsConstructor  // Default constructor
@AllArgsConstructor // Parameterized constructor
public class LogRequest {

    @JsonProperty("stack")
    private String stack;

    @JsonProperty("level")
    private String level;

    @JsonProperty("package")
    private String packageName;

    @JsonProperty("message")
    private String message;
}

