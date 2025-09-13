package com.trading.simulator.service;


import com.trading.simulator.entity.PortfolioEntry;
import com.trading.simulator.entity.User;
import com.trading.simulator.entity.Wallet;
import com.trading.simulator.repository.PortfolioRepository;
import com.trading.simulator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradingService {
    @Autowired private WalletService walletService;
    @Autowired private UserRepository userRepository;
    @Autowired private PortfolioRepository portfolioRepository;
    @Autowired private StockService stockService;

    public void buy(String username, String symbol, double quantity) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Wallet wallet = walletService.getWalletForUser(username);
        Double price = stockService.fetchLivePrice(symbol);
        if (price == null) throw new RuntimeException("Cannot fetch live price");
        double cost = price * quantity;
        if (wallet.getBalance() < cost) throw new RuntimeException("Insufficient balance");
        // deduct
        wallet.setBalance(wallet.getBalance() - cost);
        walletService.updateBalance(wallet);
        // update portfolio
        Optional<PortfolioEntry> existing = portfolioRepository.findByUserAndSymbol(user, symbol);
        if (existing.isPresent()) {
            PortfolioEntry e = existing.get();
            double newQty = e.getQuantity() + quantity;
            double newInvested = e.getInvestedAmount() + cost;
            double newAvg = newInvested / newQty;
            e.setQuantity(newQty);
            e.setInvestedAmount(newInvested);
            e.setAvgPrice(newAvg);
            portfolioRepository.save(e);
        } else {
            PortfolioEntry e = PortfolioEntry.builder()
                    .user(user).symbol(symbol).quantity(quantity).investedAmount(cost).avgPrice(price)
                    .build();
            portfolioRepository.save(e);
        }
    }

    public void sell(String username, String symbol, double quantity) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Optional<PortfolioEntry> entryOpt = portfolioRepository.findByUserAndSymbol(user, symbol);
        if (entryOpt.isEmpty()) throw new RuntimeException("No holdings found");
        PortfolioEntry entry = entryOpt.get();
        if (entry.getQuantity() < quantity) throw new RuntimeException("Not enough quantity");
        Double price = stockService.fetchLivePrice(symbol);
        if (price == null) throw new RuntimeException("Cannot fetch live price");
        double proceeds = price * quantity;
        // update wallet
        Wallet wallet = walletService.getWalletForUser(username);
        wallet.setBalance(wallet.getBalance() + proceeds);
        walletService.updateBalance(wallet);
        // update portfolio
        double remainingQty = entry.getQuantity() - quantity;
        if (remainingQty <= 0.000001) {
            portfolioRepository.delete(entry);
        } else {
            double remainingInvested = entry.getInvestedAmount() * (remainingQty / (entry.getQuantity()));
            entry.setQuantity(remainingQty);
            entry.setInvestedAmount(remainingInvested);
            // avgPrice could remain same or be recalculated
            portfolioRepository.save(entry);
        }
    }

    public List<PortfolioEntry> getPortfolio(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return portfolioRepository.findByUser(user);
    }
}
