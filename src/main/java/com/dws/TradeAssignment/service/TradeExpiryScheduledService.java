package com.dws.TradeAssignment.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TradeExpiryScheduledService {


    private static final Logger log = LoggerFactory.getLogger(TradeExpiryScheduledService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    TradeService tradeService;

    @Scheduled(cron = "${trade.expiry.run.schedule}")
    public void callAsPerSchedule() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        tradeService.updateExpiredTrades();
    }
}