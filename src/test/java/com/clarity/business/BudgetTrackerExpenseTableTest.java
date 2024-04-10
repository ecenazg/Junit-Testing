package com.clarity.business;

import com.client.junit.business.services.BudgetTracker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;
public class BudgetTrackerExpenseTableTest {
    private BudgetTracker budgetTracker;
    @Before
    public void setUp() {
        budgetTracker = new BudgetTracker();
    }
    private void setInitialBalance(BigDecimal balance) {
        // Mocked method for setting initial balance;
        // you might need to adjust based on your actual implementation.
        budgetTracker.addIncome("Initial Balance", balance);
    }
    @Test
    public void testAddHighValueExpense() {
        setInitialBalance(new BigDecimal("2000"));
        budgetTracker.addExpense("Rent", new BigDecimal("1000"));
        assertEquals(new BigDecimal("1000"), budgetTracker.getCurrentBalance());
    }
    @Test
    public void testAddModerateExpenseAfterPreviousOne() {
        setInitialBalance(new BigDecimal("2000"));
        budgetTracker.addExpense("Rent", new BigDecimal("1000"));
        budgetTracker.addExpense("Groceries", new BigDecimal("150"));
        assertEquals(new BigDecimal("850"), budgetTracker.getCurrentBalance());
    }
    @Test
    public void testAddSmallDecimalValueExpense() {
        setInitialBalance(new BigDecimal("2000"));
        budgetTracker.addExpense("Rent", new BigDecimal("1000"));
        budgetTracker.addExpense("Groceries", new BigDecimal("150"));
        budgetTracker.addExpense("Utilities", new BigDecimal("75.50"));
        assertEquals(new BigDecimal("774.50"), budgetTracker.getCurrentBalance());
    }
    @Test
    public void testAddZeroAmountExpense() {
        setInitialBalance(new BigDecimal("2000"));
        budgetTracker.addExpense("Subscription", BigDecimal.ZERO);
        assertEquals(new BigDecimal("2000"), budgetTracker.getCurrentBalance()); // Balance remains unchanged
    }
}
