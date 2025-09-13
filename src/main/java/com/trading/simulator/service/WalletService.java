package com.trading.simulator.service;

import com.trading.simulator.entity.User;
import com.trading.simulator.entity.Wallet;
import com.trading.simulator.repository.UserRepository;
import com.trading.simulator.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class WalletService {
    @Autowired private WalletRepository walletRepository;
    @Autowired private UserRepository userRepository;

    public Wallet getWalletForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return walletRepository.findByUserId(user.getId());
    }

    public Wallet addDummyMoney(String username, double amount) {
        Wallet w = getWalletForUser(username);
        w.setBalance(w.getBalance() + amount);
        return walletRepository.save(w);
    }

    public Wallet updateBalance(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}
