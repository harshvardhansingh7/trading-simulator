package com.trading.simulator.controller;


import com.trading.simulator.dto.WalletDto;
import com.trading.simulator.entity.Wallet;
import com.trading.simulator.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired private WalletService walletService;

    @GetMapping
    public WalletDto getWallet(Authentication authentication) {
        String username = authentication.getName();
        Wallet w = walletService.getWalletForUser(username);
        return new WalletDto(w.getId(), w.getBalance());
    }

    @PostMapping("/add-dummy")
    public WalletDto addDummyMoney(Authentication authentication,
                                   @RequestParam(defaultValue = "100000") double amount) {
        String username = authentication.getName();
        Wallet w = walletService.addDummyMoney(username, amount);
        return new WalletDto(w.getId(), w.getBalance());
    }
}
