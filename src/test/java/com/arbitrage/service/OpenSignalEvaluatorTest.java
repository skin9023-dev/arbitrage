package com.arbitrage.service;

import com.arbitrage.config.StrategyProperties;
import com.arbitrage.domain.CloseSignalDecision;
import com.arbitrage.domain.CloseSignalRequest;
import com.arbitrage.domain.OpenSignalDecision;
import com.arbitrage.domain.OpenSignalRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OpenSignalEvaluatorTest {

    @Test
    void shouldOpenWhenFundingAndBasisAndConfirmAllPass() {
        StrategyProperties p = new StrategyProperties();
        p.setFundingThresholdPercent(new BigDecimal("0.08"));
        p.setBasisThresholdPercent(new BigDecimal("0.20"));
        p.setConfirmSeconds(8);

        OpenSignalEvaluator evaluator = new OpenSignalEvaluator(p);

        OpenSignalRequest req = new OpenSignalRequest();
        req.setPerpSellPrice(new BigDecimal("101"));
        req.setSpotBuyPrice(new BigDecimal("100"));
        req.setExpectedFundingPercent(new BigDecimal("0.35"));
        req.setTotalCostPercent(new BigDecimal("0.10"));
        req.setConfirmSecondsObserved(10);

        OpenSignalDecision decision = evaluator.evaluateFundingPlusBasis(req);

        assertTrue(decision.isOpen());
    }

    @Test
    void shouldNotOpenWhenBasisFails() {
        StrategyProperties p = new StrategyProperties();
        p.setFundingThresholdPercent(new BigDecimal("0.08"));
        p.setBasisThresholdPercent(new BigDecimal("0.20"));
        p.setConfirmSeconds(8);

        OpenSignalEvaluator evaluator = new OpenSignalEvaluator(p);

        OpenSignalRequest req = new OpenSignalRequest();
        req.setPerpSellPrice(new BigDecimal("100.1"));
        req.setSpotBuyPrice(new BigDecimal("100"));
        req.setExpectedFundingPercent(new BigDecimal("0.35"));
        req.setTotalCostPercent(new BigDecimal("0.10"));
        req.setConfirmSecondsObserved(10);

        OpenSignalDecision decision = evaluator.evaluateFundingPlusBasis(req);

        assertFalse(decision.isOpen());
    }

    @Test
    void shouldCloseWhenNetBasisFallsBelowCloseThreshold() {
        StrategyProperties p = new StrategyProperties();
        p.setBasisCloseThresholdPercent(new BigDecimal("0.03"));

        OpenSignalEvaluator evaluator = new OpenSignalEvaluator(p);

        CloseSignalRequest req = new CloseSignalRequest();
        req.setPerpBuyPrice(new BigDecimal("100.02"));
        req.setSpotSellPrice(new BigDecimal("100"));
        req.setTotalCostPercent(new BigDecimal("0.02"));

        CloseSignalDecision decision = evaluator.evaluateBasisClose(req);

        assertTrue(decision.isClose());
    }
}
