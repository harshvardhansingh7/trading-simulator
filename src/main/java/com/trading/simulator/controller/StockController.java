package com.trading.simulator.controller;

import com.trading.simulator.dto.StockDto;
import com.trading.simulator.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired private StockService stockService;

    @GetMapping("/top")
    public List<Map<String,Object>> topStocks() {
        return stockService.fetchTopStocks();
    }

    @GetMapping("/{symbol}")
    public StockDto getStock(@PathVariable String symbol) {
        return stockService.fetchStockDetails(symbol);
    }

    @GetMapping("/{symbol}/ai")
    public Map getAi(@PathVariable String symbol) {
        return stockService.fetchAiAnalysis(symbol);
    }

    @GetMapping("/{symbol}/price")
    public Double livePrice(@PathVariable String symbol) {
        return stockService.fetchLivePrice(symbol);
    }
}
