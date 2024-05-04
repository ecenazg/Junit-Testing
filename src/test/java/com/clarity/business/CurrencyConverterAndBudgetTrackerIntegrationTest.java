package com.clarity.business;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import com.client.junit.business.services.StaticCurrencyConverter;
import com.client.junit.model.*;

import com.client.junit.business.services.BudgetTracker;


public class CurrencyConverterAndBudgetTrackerIntegrationTest {
    private StaticCurrencyConverter currencyConverter;
    private BudgetTracker budgetTracker;

    @Before
    public void setUp() {
        currencyConverter = new StaticCurrencyConverter(); // Real implementation
        budgetTracker = mock(BudgetTracker.class); // Mocking BudgetTracker

        // Setting up mock behavior for BudgetTracker
        when(budgetTracker.getTotalIncome()).thenReturn(new BigDecimal("1000"));
        when(budgetTracker.getTotalExpenses()).thenReturn(new BigDecimal("300"));
    }

    @Test
    public void testCurrencyConversionOnBalances() {
        BigDecimal incomeUSD = currencyConverter.convert(new Currency(CurrencyType.EURO, new AmountImpl(budgetTracker.getTotalIncome(), CurrencyType.EURO)), CurrencyType.DOLLAR).getAmount().getValue();
        BigDecimal expensesUSD = currencyConverter.convert(new Currency(CurrencyType.EURO, new AmountImpl(budgetTracker.getTotalExpenses(), CurrencyType.EURO)), CurrencyType.DOLLAR).getAmount().getValue();

        BigDecimal expectedIncomeUSD = new BigDecimal("1100.00");
        BigDecimal expectedExpensesUSD = new BigDecimal("330.00");

        assertEquals(0, expectedIncomeUSD.compareTo(incomeUSD.setScale(2, BigDecimal.ROUND_HALF_UP)));
        assertEquals(0, expectedExpensesUSD.compareTo(expensesUSD.setScale(2, BigDecimal.ROUND_HALF_UP)));
    }
}
