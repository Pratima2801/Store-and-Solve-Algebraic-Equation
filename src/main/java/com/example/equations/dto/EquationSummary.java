package com.example.equations.dto;

public class EquationSummary {
    private long equationId;
    private String equation;

    public EquationSummary(long equationId, String equation) {
        this.equationId = equationId;
        this.equation = equation;
    }

    public long getEquationId() { return equationId; }
    public String getEquation() { return equation; }
}
