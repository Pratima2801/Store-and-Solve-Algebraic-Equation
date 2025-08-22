package com.example.equations.util;

import com.example.equations.exception.BadRequestException;
import com.example.equations.model.Token;
import java.util.ArrayList;
import java.util.List;

public final class InfixTokenizer {
    private InfixTokenizer() {}

    public static List<Token> tokenize(String s) {
        if (s == null) throw new BadRequestException("Equation is null");
        String input = s.replaceAll("\\s+", "");
        List<Token> out = new ArrayList<>();
        int n = input.length();
        int i = 0;

        while (i < n) {
            char c = input.charAt(i);

            if (Character.isDigit(c) || (c == '.' )) {
                int j = i + 1;
                boolean dotSeen = (c == '.');
                while (j < n) {
                    char d = input.charAt(j);
                    if (Character.isDigit(d)) { j++; }
                    else if (d == '.' && !dotSeen) { dotSeen = true; j++; }
                    else break;
                }
                out.add(new Token(Token.Type.NUMBER, input.substring(i, j)));
                i = j;
                if (i < n) {
                    char nxt = input.charAt(i);
                    if (Character.isLetter(nxt) || nxt == '(') {
                        out.add(new Token(Token.Type.OPERATOR, "*"));
                    }
                }
                continue;
            }

            if (Character.isLetter(c)) {
                int j = i + 1;
                while (j < n && Character.isLetterOrDigit(input.charAt(j))) j++;
                out.add(new Token(Token.Type.VARIABLE, input.substring(i, j)));
                i = j;

                if (i < n) {
                    char nxt = input.charAt(i);
                    if (nxt == '(' || Character.isLetter(nxt) || Character.isDigit(nxt) || nxt == '.') {
                        out.add(new Token(Token.Type.OPERATOR, "*"));
                    }
                }
                continue;
            }

            if (c == '(') {
                out.add(new Token(Token.Type.LPAREN, "("));
                i++;
                continue;
            }
            if (c == ')') {
                out.add(new Token(Token.Type.RPAREN, ")"));
                i++;
               
                if (i < n) {
                    char nxt = input.charAt(i);
                    if (nxt == '(' || Character.isLetter(nxt) || Character.isDigit(nxt) || nxt == '.') {
                        out.add(new Token(Token.Type.OPERATOR, "*"));
                    }
                }
                continue;
            }

            if ("+-*/^".indexOf(c) >= 0) {
                out.add(new Token(Token.Type.OPERATOR, String.valueOf(c)));
                i++;
                continue;
            }

            throw new BadRequestException("Invalid character: " + c);
        }

        return out;
    }
}
