package com.example.equations.model;

public class Token {
    public enum Type { NUMBER, VARIABLE, OPERATOR, LPAREN, RPAREN }

    private final Type type;
    private final String text;

    public Token(Type type, String text){
        this.type = type;
        this.text = text;
    }
    public Type getType(){
        return type;
    }
    public String getText(){
        return text;
    }

    @Override public String toString(){
        return type + ":" + text;
    }
}
