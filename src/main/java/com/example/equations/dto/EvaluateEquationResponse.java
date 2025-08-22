package com.example.equations.dto;

import java.util.Map;

public class EvaluateEquationResponse {
    private long equationId;
    private String equation;
    private Map<String, Double> variables;
    private double result;

    public EvaluateEquationResponse(long equationId, String equation, Map<String, Double> variables, double result) {
        this.equationId = equationId;
        this.equation = equation;
        this.variables = variables;
        this.result = result;
    }

    public long getEquationId() { 
        return equationId; 
    }
    public String getEquation() { 
        return equation; 
    }
    public Map<String, Double> getVariables() { 
        return variables; 
    }
    public double getResult() { 
        return result; 
    }
}
