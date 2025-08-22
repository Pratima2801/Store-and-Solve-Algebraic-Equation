package com.example.equations.util;

import com.example.equations.exception.BadRequestException;
import com.example.equations.model.ExpressionTreeNode;
import com.example.equations.model.Token;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public final class PostfixTreeBuilder {
    private PostfixTreeBuilder() {}

    public static ExpressionTreeNode build(List<Token> postfix) {
        Deque<ExpressionTreeNode> st = new ArrayDeque<>();
        for (Token t : postfix) {
            switch (t.getType()) {
                case NUMBER:
                case VARIABLE:
                    st.push(new ExpressionTreeNode(t.getText(), null, null));
                    break;
                case OPERATOR:
                    if (st.size() < 2) throw new BadRequestException("Invalid expression near operator " + t.getText());
                    ExpressionTreeNode right = st.pop();
                    ExpressionTreeNode left = st.pop();
                    st.push(new ExpressionTreeNode(t.getText(), left, right));
                    break;
                default:
                    throw new BadRequestException("Unexpected token in postfix: " + t);
            }
        }
        if (st.size() != 1) throw new BadRequestException("Invalid postfix expression");
        return st.pop();
    }
}
