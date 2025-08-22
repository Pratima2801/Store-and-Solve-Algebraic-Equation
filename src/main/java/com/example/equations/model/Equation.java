package com.example.equations.model;

public class Equation {
    private final long id;
    private final String originalEquation;
    private final ExpressionTreeNode root;

    public Equation(long id, String originalEquation, ExpressionTreeNode root){
        this.id = id;
        this.originalEquation = originalEquation;
        this.root = root;
    }
    public long getId(){ 
        return id; 
    }
    public String getOriginalEquation(){ 
        return originalEquation; 
    }
    public ExpressionTreeNode getRoot(){ 
        return root; 
    }
}
