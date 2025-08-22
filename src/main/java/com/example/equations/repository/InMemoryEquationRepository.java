package com.example.equations.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.equations.model.Equation;

@Repository
public class InMemoryEquationRepository {
    private final Map<Long, Equation> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public long save(String original, com.example.equations.model.ExpressionTreeNode root) {
        long id = seq.getAndIncrement();
        store.put(id, new Equation(id, original, root));
        return id;
    }

    public Optional<Equation> find(long id) { 
        return Optional.ofNullable(store.get(id)); 
    }
    public List<Equation> findAll() { 
        return new ArrayList<>(store.values()); 
    }
}
