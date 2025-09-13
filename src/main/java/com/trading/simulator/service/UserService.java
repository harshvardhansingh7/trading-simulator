package com.trading.simulator.service;

import com.trading.simulator.entity.User;
import com.trading.simulator.entity.Wallet;
import com.trading.simulator.repository.UserRepository;
import com.trading.simulator.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signup(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already taken");
        }

        // Create user
        User u = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles("ROLE_USER")
                .build();

        User saved = userRepository.save(u);

        // Create wallet with initial balance 0
        Wallet w = Wallet.builder()
                .balance(0.0)
                .user(saved)
                .build();

        walletRepository.save(w);

        // Associate wallet with user
        saved.setWallet(w);
        return userRepository.save(saved);
    }
}
