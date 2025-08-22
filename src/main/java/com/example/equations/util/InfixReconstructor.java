package com.example.equations.util;

import com.example.equations.model.ExpressionTreeNode;
import com.example.equations.model.Operator;

/**
 * Rebuilds a readable infix string from an expression tree.
 * Adds parentheses only when needed to preserve precedence/associativity.
 */
public final class InfixReconstructor {
    private InfixReconstructor() {}

    public static String toInfix(ExpressionTreeNode root) {
        if (root == null) return "";
        return visit(root, null);
    }

    private static String visit(ExpressionTreeNode node, Operator parentOp) {
        if (node == null) return "";

        if (!node.isOperator()) {
            return node.getValue();
        }

        Operator op = Operator.from(node.getValue());

        String leftStr  = visit(node.getLeft(),  op);
        String rightStr = visit(node.getRight(), op);

        // Wrap children when their precedence is lower than current,
        // or when associativity would change meaning.
        if (node.getLeft() != null && node.getLeft().isOperator()) {
            Operator lo = Operator.from(node.getLeft().getValue());
            if (lo.precedence < op.precedence) {
                leftStr = "(" + leftStr + ")";
            }
        }
        if (node.getRight() != null && node.getRight().isOperator()) {
            Operator ro = Operator.from(node.getRight().getValue());
            boolean needParen = ro.precedence < op.precedence
                    || (ro.precedence == op.precedence && !op.rightAssoc);
            if (needParen) {
                rightStr = "(" + rightStr + ")";
            }
        }

        String expr = leftStr + " " + op.symbol + " " + rightStr;

        // If parent has higher precedence, no parens needed.
        // If parent has lower precedence, we should have already parenthesized children accordingly.
        // When equal precedence and parent is right-assoc, keep as is.
        if (parentOp != null) {
            if (op.precedence < parentOp.precedence) {
                expr = "(" + expr + ")";
            }
            // equal precedence usually ok without extra parens due to the child-side rules above
        }

        return expr;
    }
}
