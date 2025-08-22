package com.example.equations.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.equations.dto.EquationSummary;
import com.example.equations.dto.EvaluateEquationRequest;
import com.example.equations.dto.EvaluateEquationResponse;
import com.example.equations.dto.StoreEquationRequest;
import com.example.equations.dto.StoreEquationResponse;
import com.example.equations.exception.BadRequestException;
import com.example.equations.model.ExpressionTreeNode;
import com.example.equations.model.Token;
import com.example.equations.repository.InMemoryEquationRepository;
import com.example.equations.util.InfixReconstructor;
import com.example.equations.util.InfixToPostfixConverter;
import com.example.equations.util.InfixTokenizer;
import com.example.equations.util.PostfixTreeBuilder;
import com.example.equations.exception.NotFoundException; 
import com.example.equations.util.EquationEvaluator; 
import java.util.Map;  


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

    public List<EquationSummary> list() {
        return repo.findAll().stream()
                .map(eq -> new EquationSummary(eq.getId(), InfixReconstructor.toInfix(eq.getRoot())))
                .collect(Collectors.toList());
    }

    public EvaluateEquationResponse evaluate(long id, EvaluateEquationRequest req) {
        if (req == null || req.getVariables() == null) {
            throw new BadRequestException("Request must contain 'variables'");
        }

        var equation = repo.find(id).orElseThrow(() ->
                new NotFoundException("Equation with id " + id + " not found"));

        Map<String, Double> vars = req.getVariables();
        double result = EquationEvaluator.evaluate(equation.getRoot(), vars);

        return new EvaluateEquationResponse(
                equation.getId(),
                InfixReconstructor.toInfix(equation.getRoot()),
                vars,
                result
        );
    }
    
}
