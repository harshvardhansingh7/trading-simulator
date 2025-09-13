package com.trading.simulator.repository;


import com.trading.simulator.entity.PortfolioEntry;
import com.trading.simulator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<PortfolioEntry, Long> {
    List<PortfolioEntry> findByUser(User user);
    Optional<PortfolioEntry> findByUserAndSymbol(User user, String symbol);
}

