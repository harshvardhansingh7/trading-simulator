package com.trading.simulator.service;


import com.trading.simulator.dto.StockDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class StockService {
    private final RestTemplate rest = new RestTemplate();

    @Value("${external.stock.details}")
    private String stockDetailsBase;

    @Value("${external.ai.analysis}")
    private String aiAnalysisBase;

    @Value("${external.live.price}")
    private String livePriceBase;

    // fetch details (fundamentals + indicators) using provided API
    public StockDto fetchStockDetails(String symbol) {
        String url = stockDetailsBase + "/" + symbol;
        // return mapped dto (we map JSON to generic map fields)
        Map resp = rest.getForObject(url, Map.class);
        StockDto dto = new StockDto();
        dto.setSymbol((String)resp.get("symbol"));
        dto.setLivePrice(resp.get("livePrice") instanceof Number ? ((Number)resp.get("livePrice")).doubleValue() : null);
        dto.setDayHigh(resp.get("dayHigh") instanceof Number ? ((Number)resp.get("dayHigh")).doubleValue() : null);
        dto.setDayLow(resp.get("dayLow") instanceof Number ? ((Number)resp.get("dayLow")).doubleValue() : null);
        dto.setFundamentals((Map)resp.get("fundamentals"));
        dto.setIndicators((Map)resp.get("indicators"));
        dto.setOverallDecision((String)resp.get("overallDecision"));
        return dto;
    }

    // fetch AI analysis
    public Map fetchAiAnalysis(String symbol) {
        String url = aiAnalysisBase + "/" + symbol;
        Map resp = rest.getForObject(url, Map.class);
        return resp;
    }

    // fetch live price
    public Double fetchLivePrice(String symbol) {
        String url = livePriceBase + "/" + symbol + "/live";
        try {
            Map resp = rest.getForObject(url, Map.class);
            if (resp == null) return null;
            Object p = resp.get("price");
            if (p instanceof Number) return ((Number)p).doubleValue();
            if (p instanceof String) return Double.parseDouble((String)p);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    // fetch top 100 stocks — for demo we return a small list or call an external aggregator if you have one
    public List<Map<String,Object>> fetchTopStocks() {
        // In real project call a real endpoint; here we show example with a static list for simplicity
        List<Map<String,Object>> list = new ArrayList<>();
        // Example few stocks — in real app get top 100 from service
        Map<String,Object> m = new HashMap<>();
        m.put("symbol", "INFY.NS");
        m.put("name", "Infosys Ltd");
        m.put("livePrice", fetchLivePrice("INFY.NS"));
        list.add(m);
        // add more as required...
        return list;
    }
}
