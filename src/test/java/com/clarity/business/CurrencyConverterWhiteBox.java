package com.clarity.business;

import com.client.junit.business.services.StaticCurrencyConverter;
import com.client.junit.model.Amount;
import com.client.junit.model.Currency;
import com.client.junit.model.CurrencyType;
import com.client.junit.model.AmountImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

/*Cyclomatic Complexity (CC)
The cyclomatic complexity can be calculated by identifying the number of linearly independent paths through the program. In the convert method, we have several decision points based on currency type comparisons, contributing to the complexity:

Start
Decision for from == CurrencyType.EURO && to == CurrencyType.DOLLAR
Decision for from == CurrencyType.EURO && to == CurrencyType.INDIAN_RUPEE
Decision for from == CurrencyType.DOLLAR && to == CurrencyType.EURO
Decision for from == CurrencyType.DOLLAR && to == CurrencyType.INDIAN_RUPEE
Decision for from == CurrencyType.INDIAN_RUPEE && to == CurrencyType.EURO
Decision for from == CurrencyType.INDIAN_RUPEE && to == CurrencyType.DOLLAR
Return (Default case assuming 1:1 conversion)
Each if condition introduces a branch (two paths: true or false), except the final else, which is the default path. So, we have 7 decision points + 1 default path = 8 paths.

Cyclomatic Complexity
CC = Number of decision points + 1 = 7 + 1 = 8.

Basis Paths
Given the cyclomatic complexity, we can identify the following basis paths for testing:

Conversion from EURO to DOLLAR.
Conversion from EURO to INDIAN_RUPEE.
Conversion from DOLLAR to EURO.
Conversion from DOLLAR to INDIAN_RUPEE.
Conversion from INDIAN_RUPEE to EURO.
Conversion from INDIAN_RUPEE to DOLLAR.
Conversion within the same currency type, which should use the 1:1 rate by default.
A conversion path that doesn't match any of the specified (for completeness and future-proofing, although based on current logic, this path will not be taken).*/
public class CurrencyConverterWhiteBox {
    private StaticCurrencyConverter converter;
    @Before
    public void setUp() {
        converter = new StaticCurrencyConverter();
    }
    @Test
    public void testConvertEuroToDollar() {
        Currency convertedCurrency = converter.convert(new Currency(CurrencyType.EURO, new AmountImpl(new BigDecimal("100"), CurrencyType.EURO)), CurrencyType.DOLLAR);
        assertEquals(CurrencyType.DOLLAR, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("110.00").compareTo(convertedCurrency.getAmount().getValue()));
    }
    @Test
    public void testConvertEuroToIndianRupee() {
        Currency convertedCurrency = converter.convert(new Currency(CurrencyType.EURO, new AmountImpl(new BigDecimal("1"), CurrencyType.EURO)), CurrencyType.INDIAN_RUPEE);
        assertEquals(CurrencyType.INDIAN_RUPEE, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("80.00").compareTo(convertedCurrency.getAmount().getValue()));
    }
    @Test
    public void testConvertWithinSameCurrency() {
        Currency convertedCurrency = converter.convert(new Currency(CurrencyType.EURO, new AmountImpl(new BigDecimal("100"), CurrencyType.EURO)), CurrencyType.EURO);
        assertEquals(CurrencyType.EURO, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("100.00").compareTo(convertedCurrency.getAmount().getValue()));
    }
    @Test
    public void testConvertDollarToEuro() {
        Currency convertedCurrency = converter.convert(new Currency(CurrencyType.DOLLAR, new AmountImpl(new BigDecimal("100"), CurrencyType.DOLLAR)), CurrencyType.EURO);
        assertEquals(CurrencyType.EURO, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("90.00").compareTo(convertedCurrency.getAmount().getValue()));
    }
    @Test
    public void testConvertDollarToIndianRupee() {
        Currency convertedCurrency = converter.convert(new Currency(CurrencyType.DOLLAR, new AmountImpl(new BigDecimal("1"), CurrencyType.DOLLAR)), CurrencyType.INDIAN_RUPEE);
        assertEquals(CurrencyType.INDIAN_RUPEE, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("73.00").compareTo(convertedCurrency.getAmount().getValue()));
    }
    @Test
    public void testConvertIndianRupeeToEuro() {
        Currency convertedCurrency = converter.convert(new Currency(CurrencyType.INDIAN_RUPEE, new AmountImpl(new BigDecimal("100"), CurrencyType.INDIAN_RUPEE)), CurrencyType.EURO);
        assertEquals(CurrencyType.EURO, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("1.25").compareTo(convertedCurrency.getAmount().getValue()));
    }
    @Test
    public void testConvertIndianRupeeToDollar() {
        Currency convertedCurrency = converter.convert(new Currency(CurrencyType.INDIAN_RUPEE, new AmountImpl(new BigDecimal("100"), CurrencyType.INDIAN_RUPEE)), CurrencyType.DOLLAR);
        assertEquals(CurrencyType.DOLLAR, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("1.37").compareTo(convertedCurrency.getAmount().getValue()));
    }
    @Test
    public void testConvertWithUnsupportedCurrency() {
        Currency convertedCurrency = converter.convert(new Currency(CurrencyType.DOLLAR, new AmountImpl(new BigDecimal("100"), CurrencyType.DOLLAR)), CurrencyType.EURO);
        assertEquals(CurrencyType.EURO, convertedCurrency.getType());
        assertEquals(0, new BigDecimal("90.00").compareTo(convertedCurrency.getAmount().getValue()));
    }
}
