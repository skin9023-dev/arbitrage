package com.arbitrage.config;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
@ConfigurationProperties(prefix = "strategy")
public class StrategyProperties {

    @DecimalMin("0.0")
    private BigDecimal fundingThresholdPercent = new BigDecimal("0.08");

    @DecimalMin("0.0")
    private BigDecimal basisThresholdPercent = new BigDecimal("0.20");

    @DecimalMin("0.0")
    private BigDecimal basisCloseThresholdPercent = new BigDecimal("0.03");

    @Min(1)
    private int confirmSeconds = 8;

    public BigDecimal getFundingThresholdPercent() {
        return fundingThresholdPercent;
    }

    public void setFundingThresholdPercent(BigDecimal fundingThresholdPercent) {
        this.fundingThresholdPercent = fundingThresholdPercent;
    }

    public BigDecimal getBasisThresholdPercent() {
        return basisThresholdPercent;
    }

    public void setBasisThresholdPercent(BigDecimal basisThresholdPercent) {
        this.basisThresholdPercent = basisThresholdPercent;
    }

    public BigDecimal getBasisCloseThresholdPercent() {
        return basisCloseThresholdPercent;
    }

    public void setBasisCloseThresholdPercent(BigDecimal basisCloseThresholdPercent) {
        this.basisCloseThresholdPercent = basisCloseThresholdPercent;
    }

    public int getConfirmSeconds() {
        return confirmSeconds;
    }

    public void setConfirmSeconds(int confirmSeconds) {
        this.confirmSeconds = confirmSeconds;
    }
}
