package com.trading.simulator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance; // in INR

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ----- Constructors -----
    public Wallet() {}

    public Wallet(Long id, double balance, User user) {
        this.id = id;
        this.balance = balance;
        this.user = user;
    }

    // ----- Getters & Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    // ----- Manual Builder -----
    public static class Builder {
        private Long id;
        private double balance;
        private User user;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Wallet build() {
            return new Wallet(id, balance, user);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
