package com.example.equations.service;

import com.example.equations.dto.*;
import com.example.equations.dto.StoreEquationResponse;
import com.example.equations.exception.BadRequestException;
import com.example.equations.model.ExpressionTreeNode;
import com.example.equations.model.*;
import com.example.equations.repository.InMemoryEquationRepository;
import com.example.equations.util.InfixToPostfixConverter;
import com.example.equations.util.InfixTokenizer;
import com.example.equations.util.PostfixTreeBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquationService {

    private final InMemoryEquationRepository repo;

    public EquationService(InMemoryEquationRepository repo) {
        this.repo = repo;
    }

    public StoreEquationResponse store(StoreEquationRequest req) {
        if (req == null || req.getEquation() == null || req.getEquation().trim().isEmpty()) {
            throw new BadRequestException("Equation must not be empty");
        }

        List<Token> tokens = InfixTokenizer.tokenize(req.getEquation());
        List<Token> postfix = InfixToPostfixConverter.toPostfix(tokens);
        ExpressionTreeNode root = PostfixTreeBuilder.build(postfix);

        long id = repo.save(req.getEquation().trim(), root);
        return new StoreEquationResponse("Equation stored successfully", id);
    }
    
}
