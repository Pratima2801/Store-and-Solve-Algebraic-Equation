package com.example.equations.util;

import java.util.Map;

import com.example.equations.exception.BadRequestException;
import com.example.equations.model.ExpressionTreeNode;
import com.example.equations.model.Operator;

public final class EquationEvaluator {
    private EquationEvaluator() {}

    public static double evaluate(ExpressionTreeNode node, Map<String, Double> vars) {
        if (node == null) throw new BadRequestException("Invalid expression tree");

        if (!node.isOperator()) {
            String val = node.getValue();
            try {
                return Double.parseDouble(val);
            } catch (NumberFormatException nfe) {
                // treat as variable
                Double v = vars.get(val);
                if (v == null) {
                    throw new BadRequestException("Missing value for variable: " + val);
                }
                return v;
            }
        }

        double left  = evaluate(node.getLeft(), vars);
        double right = evaluate(node.getRight(), vars);

        Operator op = Operator.from(node.getValue());
        switch (op) {
            case ADD: return left + right;
            case SUB: return left - right;
            case MUL: return left * right;
            case DIV:
                if (right == 0.0) throw new BadRequestException("Division by zero");
                return left / right;
            case POW:
                return Math.pow(left, right);
            default:
                throw new BadRequestException("Unsupported operator: " + node.getValue());
        }
    }
}
