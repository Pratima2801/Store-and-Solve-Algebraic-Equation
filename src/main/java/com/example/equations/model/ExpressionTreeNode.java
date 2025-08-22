package com.example.equations.model;

public class ExpressionTreeNode {
    private final String value; 
    private final ExpressionTreeNode left;
    private final ExpressionTreeNode right;

    public ExpressionTreeNode(String value, ExpressionTreeNode left, ExpressionTreeNode right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public String getValue(){
         return value; 
        }
    public ExpressionTreeNode getLeft(){
         return left; 
        }
    public ExpressionTreeNode getRight(){
         return right; 
        }

    public boolean isOperator(){ 
        return Operator.isOperator(value); 
    }
}
