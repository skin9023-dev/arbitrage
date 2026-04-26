package com.arbitrage.domain;

import java.math.BigDecimal;

public class OpenSignalDecision {

    private boolean open;
    private BigDecimal netFundingPercent;
    private BigDecimal grossBasisPercent;
    private BigDecimal netBasisPercent;
    private String reason;

    public static OpenSignalDecision of(boolean open,
                                        BigDecimal netFundingPercent,
                                        BigDecimal grossBasisPercent,
                                        BigDecimal netBasisPercent,
                                        String reason) {
        OpenSignalDecision d = new OpenSignalDecision();
        d.open = open;
        d.netFundingPercent = netFundingPercent;
        d.grossBasisPercent = grossBasisPercent;
        d.netBasisPercent = netBasisPercent;
        d.reason = reason;
        return d;
    }

    public boolean isOpen() {
        return open;
    }

    public BigDecimal getNetFundingPercent() {
        return netFundingPercent;
    }

    public BigDecimal getGrossBasisPercent() {
        return grossBasisPercent;
    }

    public BigDecimal getNetBasisPercent() {
        return netBasisPercent;
    }

    public String getReason() {
        return reason;
    }
}
