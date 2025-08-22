package com.example.equations.dto;

import java.util.Map;

import jakarta.validation.constraints.NotNull;

public class EvaluateEquationRequest {
    @NotNull(message = "variables map is required")
    private Map<String, Double> variables;

    public Map<String, Double> getVariables() { 
        return variables; 
    }
    public void setVariables(Map<String, Double> variables) { 
        this.variables = variables; 
    }
}
