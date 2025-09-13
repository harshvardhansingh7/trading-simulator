package com.trading.simulator.dto;

import java.util.Map;
import java.util.Objects;

public class StockDto {
    private String symbol;
    private Double livePrice;
    private Double dayHigh;
    private Double dayLow;
    private Map<String, Object> fundamentals;
    private Map<String, Object> indicators;
    private String overallDecision;

    // No-args constructor
    public StockDto() {}

    // ----- Getters & Setters -----
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public Double getLivePrice() { return livePrice; }
    public void setLivePrice(Double livePrice) { this.livePrice = livePrice; }

    public Double getDayHigh() { return dayHigh; }
    public void setDayHigh(Double dayHigh) { this.dayHigh = dayHigh; }

    public Double getDayLow() { return dayLow; }
    public void setDayLow(Double dayLow) { this.dayLow = dayLow; }

    public Map<String, Object> getFundamentals() { return fundamentals; }
    public void setFundamentals(Map<String, Object> fundamentals) { this.fundamentals = fundamentals; }

    public Map<String, Object> getIndicators() { return indicators; }
    public void setIndicators(Map<String, Object> indicators) { this.indicators = indicators; }

    public String getOverallDecision() { return overallDecision; }
    public void setOverallDecision(String overallDecision) { this.overallDecision = overallDecision; }

    // ----- equals & hashCode -----
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockDto)) return false;
        StockDto stockDto = (StockDto) o;
        return Objects.equals(symbol, stockDto.symbol) &&
                Objects.equals(livePrice, stockDto.livePrice) &&
                Objects.equals(dayHigh, stockDto.dayHigh) &&
                Objects.equals(dayLow, stockDto.dayLow) &&
                Objects.equals(fundamentals, stockDto.fundamentals) &&
                Objects.equals(indicators, stockDto.indicators) &&
                Objects.equals(overallDecision, stockDto.overallDecision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, livePrice, dayHigh, dayLow, fundamentals, indicators, overallDecision);
    }

    // ----- toString -----
    @Override
    public String toString() {
        return "StockDto{" +
                "symbol='" + symbol + '\'' +
                ", livePrice=" + livePrice +
                ", dayHigh=" + dayHigh +
                ", dayLow=" + dayLow +
                ", fundamentals=" + fundamentals +
                ", indicators=" + indicators +
                ", overallDecision='" + overallDecision + '\'' +
                '}';
    }
}
