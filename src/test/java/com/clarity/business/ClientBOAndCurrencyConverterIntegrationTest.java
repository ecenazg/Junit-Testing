package com.clarity.business;

import static org.mockito.Mockito.*;

import com.client.junit.business.services.StaticCurrencyConverter;
import com.client.junit.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.client.junit.business.ClientBO;
import com.client.junit.business.ClientBOImpl;
import com.client.junit.business.services.BudgetTracker;
import com.client.junit.business.services.DifferentCurrenciesException;

public class ClientBOAndCurrencyConverterIntegrationTest {
    private ClientBO clientBO;
    private StaticCurrencyConverter currencyConverter;

    @Before
    public void setUp() {
        clientBO = new ClientBOImpl(); // Real implementation
        currencyConverter = mock(StaticCurrencyConverter.class); // Mocked converter

        // Setting up mock behavior
        when(currencyConverter.convert(any(Currency.class), eq(CurrencyType.DOLLAR)))
                .thenAnswer(invocation -> {
                    Currency source = invocation.getArgument(0);
                    BigDecimal convertedValue = source.getAmount().getValue().multiply(new BigDecimal("1.1"));
                    return new Currency(CurrencyType.DOLLAR, new AmountImpl(convertedValue, CurrencyType.DOLLAR));
                });
    }

    @Test
    public void testProductSumAndConvertCurrency() throws DifferentCurrenciesException {
        List<Product> products = Arrays.asList(
                new ProductImpl(1, "Widget", ProductType.BANK_GUARANTEE, new AmountImpl(new BigDecimal("100"), CurrencyType.EURO)),
                new ProductImpl(2, "Gadget", ProductType.CREDIT, new AmountImpl(new BigDecimal("200"), CurrencyType.EURO))
        );

        Amount euroSum = clientBO.getClientProductsSum(products);
        Currency dollarCurrency = currencyConverter.convert(new Currency(CurrencyType.EURO, euroSum), CurrencyType.DOLLAR);

        assertEquals(new BigDecimal("330.00"), dollarCurrency.getAmount().getValue().setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}