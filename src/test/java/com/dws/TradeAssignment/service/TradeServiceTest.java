package com.dws.TradeAssignment.service;



import com.dws.TradeAssignment.entity.Trade;
import com.dws.TradeAssignment.entity.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TradeServiceTest {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    @Transactional
    public void testSaveTradeSuccess() {
        Trade trade = new Trade();
        trade.setTradeId("AB1");
        trade.setVersion(1);
        trade.setCounterPartyId("AB1");
        trade.setBookId("ABB1");
        trade.setMaturityDate(LocalDate.of(2025, 3, 15));

        Trade savedTrade = tradeService.saveTrade(trade);

        assertNotNull(savedTrade);
        assertEquals("AB1", savedTrade.getTradeId());
        assertEquals(1, savedTrade.getVersion());
    }

    @Test
    @Transactional
    public void testSaveTradeLowerVersion() {
        Trade trade = new Trade();
        trade.setTradeId("AB2");
        trade.setVersion(2);
        trade.setCounterPartyId("AB-2");
        trade.setBookId("ZR");
        trade.setMaturityDate(LocalDate.of(2024, 10, 10));

        tradeService.saveTrade(trade);

        Trade lowerVersionTrade = new Trade();
        lowerVersionTrade.setTradeId("AB2");
        lowerVersionTrade.setVersion(1);
        lowerVersionTrade.setCounterPartyId("AB-2");
        lowerVersionTrade.setBookId("ZR");
        lowerVersionTrade.setMaturityDate(LocalDate.of(2025, 10, 10));

        assertThrows(IllegalArgumentException.class, () -> tradeService.saveTrade(lowerVersionTrade));
    }

    @Test
    @Transactional
    public void testSaveTradeExpired() {
        Trade trade = new Trade();
        trade.setTradeId("AB3");
        trade.setVersion(1);
        trade.setCounterPartyId("3AB");
        trade.setBookId("ZZ");
        trade.setMaturityDate(LocalDate.of(2022, 1, 1));

        assertThrows(IllegalArgumentException.class, () -> tradeService.saveTrade(trade));
    }

    @Test
    @Transactional
    public void testUpdateExpiredTrades() {
        Trade trade = new Trade();
        trade.setTradeId("AB4");
        trade.setVersion(1);
        trade.setCounterPartyId("AB-4");
        trade.setBookId("ABB3");
        trade.setMaturityDate(LocalDate.now() );

        tradeService.saveTrade(trade);
        tradeService.updateExpiredTrades();

        Trade expiredTrade = tradeRepository.findById("AB4").orElseThrow();
        assertFalse(expiredTrade.isExpired());
    }
}
