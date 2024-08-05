package com.dws.TradeAssignment.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade, String> {
    Optional<Trade> findByTradeIdAndVersion(String tradeId, int version);
}
