package com.trading.simulator.controller;


import com.trading.simulator.dto.PortfolioDto;
import com.trading.simulator.entity.PortfolioEntry;
import com.trading.simulator.service.StockService;
import com.trading.simulator.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    @Autowired private TradingService tradingService;
    @Autowired private StockService stockService;

    @GetMapping
    public List<PortfolioDto> getPortfolio(Authentication authentication) {
        List<PortfolioEntry> entries = tradingService.getPortfolio(authentication.getName());
        return entries.stream().map(e -> {
            PortfolioDto d = new PortfolioDto();
            d.setSymbol(e.getSymbol());
            d.setQuantity(e.getQuantity());
            d.setAvgPrice(e.getAvgPrice());
            d.setInvestedAmount(e.getInvestedAmount());
            Double current = stockService.fetchLivePrice(e.getSymbol());
            d.setCurrentPrice(current == null ? 0.0 : current);
            d.setPnl((current == null ? 0.0 : current * e.getQuantity()) - e.getInvestedAmount());
            return d;
        }).collect(Collectors.toList());
    }
}
