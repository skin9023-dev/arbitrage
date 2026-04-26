package com.arbitrage.domain;

import java.math.BigDecimal;

public class CloseSignalDecision {

    private boolean close;
    private BigDecimal grossBasisPercent;
    private BigDecimal netBasisPercent;
    private String reason;

    public static CloseSignalDecision of(boolean close,
                                         BigDecimal grossBasisPercent,
                                         BigDecimal netBasisPercent,
                                         String reason) {
        CloseSignalDecision d = new CloseSignalDecision();
        d.close = close;
        d.grossBasisPercent = grossBasisPercent;
        d.netBasisPercent = netBasisPercent;
        d.reason = reason;
        return d;
    }

    public boolean isClose() {
        return close;
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
