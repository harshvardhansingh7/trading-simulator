package com.trading.simulator.dto;

import java.util.Objects;

public class WalletDto {
    private Long walletId;
    private double balance;

    public WalletDto() {}
    public WalletDto(Long walletId, double balance) {
        this.walletId = walletId;
        this.balance = balance;
    }

    public Long getWalletId() { return walletId; }
    public void setWalletId(Long walletId) { this.walletId = walletId; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WalletDto)) return false;
        WalletDto walletDto = (WalletDto) o;
        return Double.compare(walletDto.balance, balance) == 0 &&
                Objects.equals(walletId, walletDto.walletId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletId, balance);
    }

    @Override
    public String toString() {
        return "WalletDto{" +
                "walletId=" + walletId +
                ", balance=" + balance +
                '}';
    }
}
