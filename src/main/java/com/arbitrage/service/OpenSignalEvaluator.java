package com.arbitrage.service;

import com.arbitrage.config.StrategyProperties;
import com.arbitrage.domain.CloseSignalDecision;
import com.arbitrage.domain.CloseSignalRequest;
import com.arbitrage.domain.OpenSignalDecision;
import com.arbitrage.domain.OpenSignalRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class OpenSignalEvaluator {

    private static final BigDecimal HUNDRED = new BigDecimal("100");

    private final StrategyProperties properties;

    public OpenSignalEvaluator(StrategyProperties properties) {
        this.properties = properties;
    }

    public OpenSignalDecision evaluateFundingPlusBasis(OpenSignalRequest request) {
        BigDecimal grossBasisPercent = calcBasisPercent(request.getPerpSellPrice(), request.getSpotBuyPrice());

        BigDecimal netFundingPercent = request.getExpectedFundingPercent()
                .subtract(request.getTotalCostPercent());

        BigDecimal netBasisPercent = grossBasisPercent
                .subtract(request.getTotalCostPercent());

        boolean fundingPass = netFundingPercent.compareTo(properties.getFundingThresholdPercent()) >= 0;
        boolean basisPass = netBasisPercent.compareTo(properties.getBasisThresholdPercent()) >= 0;
        boolean confirmPass = request.getConfirmSecondsObserved() >= properties.getConfirmSeconds();

        boolean open = fundingPass && basisPass && confirmPass;

        String reason = "fundingPass=" + fundingPass
                + ", basisPass=" + basisPass
                + ", confirmPass=" + confirmPass;

        return OpenSignalDecision.of(open,
                scale(netFundingPercent),
                scale(grossBasisPercent),
                scale(netBasisPercent),
                reason);
    }

    public CloseSignalDecision evaluateBasisClose(CloseSignalRequest request) {
        BigDecimal grossBasisPercent = calcBasisPercent(request.getPerpBuyPrice(), request.getSpotSellPrice());
        BigDecimal netBasisPercent = grossBasisPercent.subtract(request.getTotalCostPercent());

        boolean closePass = netBasisPercent.compareTo(properties.getBasisCloseThresholdPercent()) <= 0;
        String reason = "closePass=" + closePass
                + ", netBasisPercent=" + scale(netBasisPercent)
                + ", closeThresholdPercent=" + properties.getBasisCloseThresholdPercent();

        return CloseSignalDecision.of(closePass, scale(grossBasisPercent), scale(netBasisPercent), reason);
    }

    private BigDecimal calcBasisPercent(BigDecimal perpPrice, BigDecimal spotPrice) {
        return perpPrice
                .subtract(spotPrice)
                .divide(spotPrice, 8, RoundingMode.HALF_UP)
                .multiply(HUNDRED);
    }

    private BigDecimal scale(BigDecimal value) {
        return value.setScale(6, RoundingMode.HALF_UP);
    }
}
