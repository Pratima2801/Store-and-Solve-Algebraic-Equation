package com.example.equations.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.equations.dto.StoreEquationRequest;
import com.example.equations.dto.StoreEquationResponse;
import com.example.equations.service.EquationService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/equations")
public class EquationController {

    private final EquationService service;

    public EquationController(EquationService service) {
        this.service = service;
    }

    @PostMapping("/store")
    public ResponseEntity<StoreEquationResponse> store(@Valid @RequestBody StoreEquationRequest request) {
        return ResponseEntity.ok(service.store(request));
    }
       
}
