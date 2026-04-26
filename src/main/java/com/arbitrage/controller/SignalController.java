package com.arbitrage.controller;

import com.arbitrage.domain.CloseSignalDecision;
import com.arbitrage.domain.CloseSignalRequest;
import com.arbitrage.domain.OpenSignalDecision;
import com.arbitrage.domain.OpenSignalRequest;
import com.arbitrage.service.OpenSignalEvaluator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signals")
public class SignalController {

    private final OpenSignalEvaluator evaluator;

    public SignalController(OpenSignalEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @PostMapping("/funding-basis/open")
    public OpenSignalDecision evaluateFundingBasisOpen(@Valid @RequestBody OpenSignalRequest request) {
        return evaluator.evaluateFundingPlusBasis(request);
    }

    @PostMapping("/funding-basis/close")
    public CloseSignalDecision evaluateFundingBasisClose(@Valid @RequestBody CloseSignalRequest request) {
        return evaluator.evaluateBasisClose(request);
    }
}
