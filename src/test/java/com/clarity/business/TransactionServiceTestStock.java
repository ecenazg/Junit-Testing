package com.clarity.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.client.junit.business.services.BudgetTracker;
import com.client.junit.business.services.StockTransaction;
import com.client.junit.business.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTestStock {

    @Mock
    private StockTransaction stockTransaction;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
    }

    @Test
    void testBuyStock_ExpectedCalls() {
        // Setup
        String stockSymbol = "AAPL";
        int numberOfShares = 50;
        BigDecimal pricePerShare = new BigDecimal("150.00");

        // Define behavior
        doNothing().when(stockTransaction).buyStock(stockSymbol, numberOfShares, pricePerShare);

        // Test
        transactionService.buyStock(stockSymbol, numberOfShares, pricePerShare);

        // Verification
        verify(stockTransaction, times(1)).buyStock(stockSymbol, numberOfShares, pricePerShare);
    }

    @Test
    void testSellStock_ExpectedCalls() {
        // Setup
        String stockSymbol = "GOOGL";
        int numberOfShares = 30;
        BigDecimal pricePerShare = new BigDecimal("1200.00");

        // Define behavior
        doNothing().when(stockTransaction).sellStock(stockSymbol, numberOfShares, pricePerShare);

        // Test
        transactionService.sellStock(stockSymbol, numberOfShares, pricePerShare);

        // Verification
        verify(stockTransaction, times(1)).sellStock(stockSymbol, numberOfShares, pricePerShare);
    }


}
