package com.dws.TradeAssignment.controller;


import com.dws.TradeAssignment.entity.Trade;
import com.dws.TradeAssignment.service.TradeExpiryScheduledService;
import com.dws.TradeAssignment.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    private static final Logger log = LoggerFactory.getLogger(TradeController.class);

    @PostMapping
    public ResponseEntity<Trade> addTrade(@RequestBody Trade trade) {
        log.debug("addTrade called");
        Trade savedTrade = tradeService.saveTrade(trade);
        return ResponseEntity.ok(savedTrade);
    }

    @GetMapping
    public ResponseEntity<List<Trade>> getAllTrades() {
        List<Trade> trades = tradeService.findAll();
        return ResponseEntity.ok(trades);
    }
}

