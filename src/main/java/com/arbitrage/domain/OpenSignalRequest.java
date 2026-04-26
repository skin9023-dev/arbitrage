package com.arbitrage.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OpenSignalRequest {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal perpSellPrice;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal spotBuyPrice;

    @NotNull
    private BigDecimal expectedFundingPercent;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal totalCostPercent;

    @NotNull
    @DecimalMin("1")
    private Integer confirmSecondsObserved;

    public BigDecimal getPerpSellPrice() {
        return perpSellPrice;
    }

    public void setPerpSellPrice(BigDecimal perpSellPrice) {
        this.perpSellPrice = perpSellPrice;
    }

    public BigDecimal getSpotBuyPrice() {
        return spotBuyPrice;
    }

    public void setSpotBuyPrice(BigDecimal spotBuyPrice) {
        this.spotBuyPrice = spotBuyPrice;
    }

    public BigDecimal getExpectedFundingPercent() {
        return expectedFundingPercent;
    }

    public void setExpectedFundingPercent(BigDecimal expectedFundingPercent) {
        this.expectedFundingPercent = expectedFundingPercent;
    }

    public BigDecimal getTotalCostPercent() {
        return totalCostPercent;
    }

    public void setTotalCostPercent(BigDecimal totalCostPercent) {
        this.totalCostPercent = totalCostPercent;
    }

    public Integer getConfirmSecondsObserved() {
        return confirmSecondsObserved;
    }

    public void setConfirmSecondsObserved(Integer confirmSecondsObserved) {
        this.confirmSecondsObserved = confirmSecondsObserved;
    }
}
