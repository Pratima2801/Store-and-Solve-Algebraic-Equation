package com.example.equations.util;

import com.example.equations.exception.BadRequestException;
import com.example.equations.model.Operator;
import com.example.equations.model.Token;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class InfixToPostfixConverter {
    private InfixToPostfixConverter() {}

    public static List<Token> toPostfix(List<Token> infix) {
        List<Token> output = new ArrayList<>();
        Deque<Token> stack = new ArrayDeque<>();

        for (int i = 0; i < infix.size(); i++) {
            Token t = infix.get(i);
            switch (t.getType()) {
                case NUMBER:
                case VARIABLE:
                    output.add(t);
                    break;

                case OPERATOR:
                    Operator o1 = Operator.from(t.getText());
                    while (!stack.isEmpty() && stack.peek().getType() == Token.Type.OPERATOR) {
                        Operator o2 = Operator.from(stack.peek().getText());
                        boolean lowerPrec =
                                (!o1.rightAssoc && o1.precedence <= o2.precedence) ||
                                (o1.rightAssoc && o1.precedence <  o2.precedence);
                        if (lowerPrec) output.add(stack.pop());
                        else break;
                    }
                    stack.push(t);
                    break;

                case LPAREN:
                    stack.push(t);
                    break;

                case RPAREN:
                    boolean foundLParen = false;
                    while (!stack.isEmpty()) {
                        Token top = stack.pop();
                        if (top.getType() == Token.Type.LPAREN) {
                            foundLParen = true; break;
                        }
                        output.add(top);
                    }
                    if (!foundLParen) throw new BadRequestException("Mismatched parentheses");
                    break;

                default:
                    throw new BadRequestException("Unexpected token: " + t);
            }
        }

        while (!stack.isEmpty()) {
            Token top = stack.pop();
            if (top.getType() == Token.Type.LPAREN || top.getType() == Token.Type.RPAREN)
                throw new BadRequestException("Mismatched parentheses");
            output.add(top);
        }
        if (output.isEmpty()) throw new BadRequestException("Empty expression");
        return output;
    }
}
