package com.trading.simulator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "portfolio")
public class PortfolioEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private double quantity;

    private double avgPrice; // average buy price per share

    private double investedAmount; // total invested

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ----- Constructors -----
    public PortfolioEntry() {}

    public PortfolioEntry(Long id, String symbol, double quantity, double avgPrice, double investedAmount, User user) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
        this.avgPrice = avgPrice;
        this.investedAmount = investedAmount;
        this.user = user;
    }

    // ----- Getters & Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getAvgPrice() { return avgPrice; }
    public void setAvgPrice(double avgPrice) { this.avgPrice = avgPrice; }

    public double getInvestedAmount() { return investedAmount; }
    public void setInvestedAmount(double investedAmount) { this.investedAmount = investedAmount; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    // ----- Manual Builder -----
    public static class Builder {
        private Long id;
        private String symbol;
        private double quantity;
        private double avgPrice;
        private double investedAmount;
        private User user;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder quantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder avgPrice(double avgPrice) {
            this.avgPrice = avgPrice;
            return this;
        }

        public Builder investedAmount(double investedAmount) {
            this.investedAmount = investedAmount;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public PortfolioEntry build() {
            return new PortfolioEntry(id, symbol, quantity, avgPrice, investedAmount, user);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
