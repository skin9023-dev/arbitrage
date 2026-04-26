package com.arbitrage.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CloseSignalRequest {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal perpBuyPrice;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal spotSellPrice;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal totalCostPercent;

    public BigDecimal getPerpBuyPrice() {
        return perpBuyPrice;
    }

    public void setPerpBuyPrice(BigDecimal perpBuyPrice) {
        this.perpBuyPrice = perpBuyPrice;
    }

    public BigDecimal getSpotSellPrice() {
        return spotSellPrice;
    }

    public void setSpotSellPrice(BigDecimal spotSellPrice) {
        this.spotSellPrice = spotSellPrice;
    }

    public BigDecimal getTotalCostPercent() {
        return totalCostPercent;
    }

    public void setTotalCostPercent(BigDecimal totalCostPercent) {
        this.totalCostPercent = totalCostPercent;
    }
}
