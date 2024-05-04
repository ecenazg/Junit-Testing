package com.clarity.business;

import static org.mockito.Mockito.*;

import com.client.junit.business.ClientBO;
import com.client.junit.business.ClientBOImpl;
import com.client.junit.business.services.BudgetTracker;
import com.client.junit.business.services.DifferentCurrenciesException;
import com.client.junit.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ClientBOAndBudgetTrackerIntegrationTest {
    private ClientBO clientBO;
    private BudgetTracker budgetTracker;

    @Before
    public void setUp() {
        clientBO = new ClientBOImpl(); // Real implementation
        budgetTracker = mock(BudgetTracker.class); // Mocking BudgetTracker

        // Setting up mock behavior for BudgetTracker
        doNothing().when(budgetTracker).addIncome(anyString(), any(BigDecimal.class));
        when(budgetTracker.getTotalIncome()).thenReturn(BigDecimal.ZERO);
    }

    @Test
    public void testAddProductSumToBudgetTracker() throws DifferentCurrenciesException {
        List<Product> products = Arrays.asList(
                new ProductImpl(1, "Widget", ProductType.LOAN, new AmountImpl(new BigDecimal("150"), CurrencyType.EURO)),
                new ProductImpl(2, "Gadget", ProductType.CREDIT, new AmountImpl(new BigDecimal("350"), CurrencyType.EURO))
        );

        Amount productSum = clientBO.getClientProductsSum(products);
        budgetTracker.addIncome("Product Sales", productSum.getValue());

        verify(budgetTracker, times(1)).addIncome(eq("Product Sales"), eq(new BigDecimal("500")));
        verify(budgetTracker, never()).addExpense(anyString(), any(BigDecimal.class));
    }
}
