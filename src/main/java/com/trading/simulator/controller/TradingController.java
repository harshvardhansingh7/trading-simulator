package com.trading.simulator.controller;


import com.trading.simulator.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
public class TradingController {
    @Autowired private TradingService tradingService;

    @PostMapping("/buy")
    public String buy(Authentication authentication,
                      @RequestParam String symbol,
                      @RequestParam double qty) {
        tradingService.buy(authentication.getName(), symbol, qty);
        return "Bought " + qty + " of " + symbol;
    }

    @PostMapping("/sell")
    public String sell(Authentication authentication,
                       @RequestParam String symbol,
                       @RequestParam double qty) {
        tradingService.sell(authentication.getName(), symbol, qty);
        return "Sold " + qty + " of " + symbol;
    }
}
