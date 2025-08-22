package com.example.equations.dto;

import jakarta.validation.constraints.NotBlank;

public class StoreEquationRequest {
    @NotBlank(message = "equation must not be blank")
    private String equation;

    public String getEquation() { 
        return equation; 
    }
    public void setEquation(String equation) { 
        this.equation = equation; 
    }
}