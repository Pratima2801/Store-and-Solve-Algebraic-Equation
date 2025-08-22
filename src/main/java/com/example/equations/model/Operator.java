package com.example.equations.model;

public enum Operator {
    ADD("+", 1, false),
    SUB("-", 1, false),
    MUL("*", 2, false),
    DIV("/", 2, false),
    POW("^", 3, true);

    public final String symbol;
    public final int precedence;
    public final boolean rightAssoc;

    Operator(String symbol, int precedence, boolean rightAssoc) {
        this.symbol = symbol; 
        this.precedence = precedence; 
        this.rightAssoc = rightAssoc;
    }

    public static boolean isOperator(String s) {
        return "+-*/^".contains(s);
    }
    public static Operator from(String s) {
        switch (s) {
            case "+": return ADD;
            case "-": return SUB;
            case "*": return MUL;
            case "/": return DIV;
            case "^": return POW;
            default: throw new IllegalArgumentException("Unknown operator: " + s);
        }
    }
}
