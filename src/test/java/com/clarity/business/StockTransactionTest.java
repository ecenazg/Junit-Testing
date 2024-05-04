package com.clarity.business;


import static org.junit.jupiter.api.Assertions.*;

import com.client.junit.business.services.StockTransaction;
import com.client.junit.model.Transaction;
import com.client.junit.model.TransactionType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class StockTransactionTest {

    private StockTransaction stockTransaction;

    @BeforeEach
    void setUp() {
        stockTransaction = new StockTransaction();
    }

    @Test
    void testBuyStockAddsTransactionCorrectly() {
        // Arrange
        String stockSymbol = "AAPL";
        int numberOfShares = 10;
        BigDecimal pricePerShare = new BigDecimal("150");

        // Act
        stockTransaction.buyStock(stockSymbol, numberOfShares, pricePerShare);
        List<Transaction> transactions = stockTransaction.getAllTransactions();

        // Assert
        assertEquals(1, transactions.size(), "There should be exactly one transaction");
        Transaction transaction = transactions.get(0);
        assertEquals(TransactionType.BUY, transaction.getTransactionType(), "Transaction type should be BUY");
        assertEquals(stockSymbol, transaction.getStockSymbol(), "Stock symbol should match");
        assertEquals(numberOfShares, transaction.getNumberOfShares(), "Number of shares should match");
        assertEquals(pricePerShare, transaction.getPricePerShare(), "Price per share should match");
    }

    @Test
    void testSellStockAddsTransactionCorrectly() {
        // Arrange
        String stockSymbol = "GOOGL";
        int numberOfShares = 5;
        BigDecimal pricePerShare = new BigDecimal("2000");

        // Act
        stockTransaction.sellStock(stockSymbol, numberOfShares, pricePerShare);
        List<Transaction> transactions = stockTransaction.getAllTransactions();

        // Assert
        assertEquals(1, transactions.size(), "There should be exactly one transaction");
        Transaction transaction = transactions.get(0);
        assertEquals(TransactionType.SELL, transaction.getTransactionType(), "Transaction type should be SELL");
        assertEquals(stockSymbol, transaction.getStockSymbol(), "Stock symbol should match");
        assertEquals(numberOfShares, transaction.getNumberOfShares(), "Number of shares should match");
        assertEquals(pricePerShare, transaction.getPricePerShare(), "Price per share should match");
    }

    @Test
    void testCalculatePortfolioValue() {
        // Arrange
        stockTransaction.buyStock("AAPL", 10, new BigDecimal("150"));
        stockTransaction.sellStock("AAPL", 5, new BigDecimal("200"));
        stockTransaction.buyStock("MSFT", 20, new BigDecimal("300"));

        // Expected calculation:
        // Buy 10 AAPL @ 150 = 1500
        // Sell 5 AAPL @ 200 = -1000
        // Buy 20 MSFT @ 300 = 6000
        // Total = 6500

        // Act
        BigDecimal portfolioValue = stockTransaction.calculatePortfolioValue();

        // Assert
        assertEquals(new BigDecimal("6500"), portfolioValue, "Portfolio value should be calculated correctly");
    }

    @Test
    void testPortfolioIsEmptyInitially() {
        // Assert
        assertTrue(stockTransaction.getAllTransactions().isEmpty(), "Transaction list should be initially empty");
    }

    @Test
    void testAddingMultipleTransactions() {
        // Arrange
        stockTransaction.buyStock("AAPL", 10, new BigDecimal("150"));
        stockTransaction.buyStock("MSFT", 20, new BigDecimal("300"));
        stockTransaction.sellStock("GOOGL", 5, new BigDecimal("2000"));

        // Act
        List<Transaction> transactions = stockTransaction.getAllTransactions();

        // Assert
        assertEquals(3, transactions.size(), "There should be three transactions");
    }
}