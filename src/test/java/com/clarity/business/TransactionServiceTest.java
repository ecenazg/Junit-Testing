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
public class TransactionServiceTest {

    @Mock
    private StockTransaction stockTransaction;

    @Mock
    private BudgetTracker budgetTracker;

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

    @Test
    void testAddIncome_CheckMultipleIncomes() {
        // Setup
        when(budgetTracker.getTotalIncome()).thenReturn(new BigDecimal("5000.00"));

        // Test and Verify
        transactionService.addIncome("Salary", new BigDecimal("2000.00"));
        transactionService.addIncome("Freelance", new BigDecimal("3000.00"));
        assertEquals(new BigDecimal("5000.00"), budgetTracker.getTotalIncome());

        verify(budgetTracker, times(1)).addIncome("Salary", new BigDecimal("2000.00"));
        verify(budgetTracker, times(1)).addIncome("Freelance", new BigDecimal("3000.00"));
    }

    @Test
    void testAddExpense_MultipleExpensesCheckBalance() {
        // Setup
        when(budgetTracker.getTotalExpenses()).thenReturn(new BigDecimal("1200.00"));
        when(budgetTracker.getCurrentBalance()).thenReturn(new BigDecimal("3800.00"));

        // Test and Verify
        transactionService.addExpense("Rent", new BigDecimal("800.00"));
        transactionService.addExpense("Utilities", new BigDecimal("400.00"));
        assertEquals(new BigDecimal("1200.00"), budgetTracker.getTotalExpenses());
        assertEquals(new BigDecimal("3800.00"), budgetTracker.getCurrentBalance());

        verify(budgetTracker, times(1)).addExpense("Rent", new BigDecimal("800.00"));
        verify(budgetTracker, times(1)).addExpense("Utilities", new BigDecimal("400.00"));
    }

    @Test
    void testComplexInteraction_MultipleTransactions() {
        // Setup
        when(budgetTracker.getTotalIncome()).thenReturn(new BigDecimal("5000.00"));
        when(budgetTracker.getTotalExpenses()).thenReturn(new BigDecimal("3000.00"));
        when(budgetTracker.getCurrentBalance()).thenReturn(new BigDecimal("2000.00"));

        // Test and Verify
        transactionService.addIncome("Salary", new BigDecimal("5000.00"));
        transactionService.addExpense("Rent", new BigDecimal("2000.00"));
        transactionService.addExpense("Shopping", new BigDecimal("1000.00"));

        assertEquals(new BigDecimal("5000.00"), budgetTracker.getTotalIncome());
        assertEquals(new BigDecimal("3000.00"), budgetTracker.getTotalExpenses());
        assertEquals(new BigDecimal("2000.00"), budgetTracker.getCurrentBalance());

        verify(budgetTracker, times(1)).addIncome("Salary", new BigDecimal("5000.00"));
        verify(budgetTracker, times(1)).addExpense("Rent", new BigDecimal("2000.00"));
        verify(budgetTracker, times(1)).addExpense("Shopping", new BigDecimal("1000.00"));
    }
}
