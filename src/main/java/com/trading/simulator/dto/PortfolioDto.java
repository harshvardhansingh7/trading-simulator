package com.trading.simulator.dto;

import java.util.Objects;

public class PortfolioDto {
    private String symbol;
    private double quantity;
    private double avgPrice;
    private double investedAmount;
    private double currentPrice;
    private double pnl; // profit & loss

    public PortfolioDto() {}

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getAvgPrice() { return avgPrice; }
    public void setAvgPrice(double avgPrice) { this.avgPrice = avgPrice; }

    public double getInvestedAmount() { return investedAmount; }
    public void setInvestedAmount(double investedAmount) { this.investedAmount = investedAmount; }

    public double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    public double getPnl() { return pnl; }
    public void setPnl(double pnl) { this.pnl = pnl; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortfolioDto)) return false;
        PortfolioDto that = (PortfolioDto) o;
        return Double.compare(that.quantity, quantity) == 0 &&
                Double.compare(that.avgPrice, avgPrice) == 0 &&
                Double.compare(that.investedAmount, investedAmount) == 0 &&
                Double.compare(that.currentPrice, currentPrice) == 0 &&
                Double.compare(that.pnl, pnl) == 0 &&
                Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, quantity, avgPrice, investedAmount, currentPrice, pnl);
    }

    @Override
    public String toString() {
        return "PortfolioDto{" +
                "symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                ", avgPrice=" + avgPrice +
                ", investedAmount=" + investedAmount +
                ", currentPrice=" + currentPrice +
                ", pnl=" + pnl +
                '}';
    }
}
