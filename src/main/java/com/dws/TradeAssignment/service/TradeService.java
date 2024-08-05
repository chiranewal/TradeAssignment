package com.dws.TradeAssignment.service;


import com.dws.TradeAssignment.entity.Trade;
import com.dws.TradeAssignment.entity.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Transactional
    public Trade saveTrade(Trade trade) {
        validateTrade(trade);

        Optional<Trade> existingTrade = tradeRepository.findByTradeIdAndVersion(trade.getTradeId(), trade.getVersion());

        if (existingTrade.isPresent()) {
            trade.setCreatedDate(LocalDate.now());
            tradeRepository.save(trade);
        } else {
            tradeRepository.findById(trade.getTradeId()).ifPresent(t -> {
                if (t.getVersion() > trade.getVersion()) {
                    throw new IllegalArgumentException("Trade version is lower than existing version");
                }
            });
            trade.setCreatedDate(LocalDate.now());
            tradeRepository.save(trade);
        }

       // updateExpiredTrades();
        return trade;
    }

    private void validateTrade(Trade trade) {
        if (trade.getMaturityDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Trade maturity date is before current date");
        }
    }

    void updateExpiredTrades() {
        tradeRepository.findAll().forEach(trade -> {
            if (trade.getMaturityDate().isBefore(LocalDate.now())) {
                trade.setExpired(true);
                tradeRepository.save(trade);
            }
        });
    }

    public List<Trade> findAll() {
       return tradeRepository.findAll();
    }
}
