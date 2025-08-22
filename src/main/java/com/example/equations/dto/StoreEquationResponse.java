package com.example.equations.dto;

public class StoreEquationResponse {
    private String message;
    private long equationId;

    public StoreEquationResponse(String message, long equationId) {
        this.message = message; this.equationId = equationId;
    }
    public String getMessage() { 
        return message; 
    }
    public long getEquationId() { 
        return equationId; 
    }
}
