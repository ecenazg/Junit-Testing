package com.clarity.business;

import com.client.junit.business.services.StaticCurrencyConverter;
import com.client.junit.model.Currency;
import com.client.junit.model.CurrencyType;
import com.client.junit.model.AmountImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

public class CurrencyConverterTest {

    private StaticCurrencyConverter converter;

    @Before
    public void setUp() {
        converter = new StaticCurrencyConverter();
    }

    @Test
    public void testEuroToDollarConversion() {
        Currency sourceCurrency = new Currency(CurrencyType.EURO, new AmountImpl(new BigDecimal("100"), CurrencyType.EURO));
        CurrencyType targetCurrencyType = CurrencyType.DOLLAR;
        Currency convertedCurrency = converter.convert(sourceCurrency, targetCurrencyType);

        assertEquals(CurrencyType.DOLLAR, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("110").compareTo(convertedCurrency.getAmount().getValue()));
    }

    @Test
    public void testDollarToEuroConversion() {
        Currency sourceCurrency = new Currency(CurrencyType.DOLLAR, new AmountImpl(new BigDecimal("100"), CurrencyType.DOLLAR));
        CurrencyType targetCurrencyType = CurrencyType.EURO;

        Currency convertedCurrency = converter.convert(sourceCurrency, targetCurrencyType);

        assertEquals(CurrencyType.EURO, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("90").compareTo(convertedCurrency.getAmount().getValue()));
    }

    @Test
    public void testSameCurrencyConversion() {
        Currency sourceCurrency = new Currency(CurrencyType.EURO, new AmountImpl(new BigDecimal("100"), CurrencyType.EURO));
        CurrencyType targetCurrencyType = CurrencyType.EURO;
        Currency convertedCurrency = converter.convert(sourceCurrency, targetCurrencyType);

        assertEquals(CurrencyType.EURO, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("100").compareTo(convertedCurrency.getAmount().getValue()));
    }
}
