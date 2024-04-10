package com.client.junit.business.services;

import com.client.junit.model.*;

import java.math.BigDecimal;

public class StaticCurrencyConverter {

    public Currency convert(Currency sourceCurrency, CurrencyType targetCurrencyType) {
        BigDecimal sourceAmount = sourceCurrency.getAmount().getValue();
        CurrencyType sourceCurrencyType = sourceCurrency.getType();

        double rate = getExchangeRate(sourceCurrencyType, targetCurrencyType);
        BigDecimal convertedAmount = sourceAmount.multiply(BigDecimal.valueOf(rate));
        BigDecimal roundedAmount = convertedAmount.setScale(2, BigDecimal.ROUND_HALF_UP);

        Amount newAmount = new AmountImpl(roundedAmount, targetCurrencyType); // Use the AmountImpl constructor
        return new Currency(targetCurrencyType, newAmount);
    }

    //TODO: Use dynamic API to get exchange rates.
    public double getExchangeRate(CurrencyType from, CurrencyType to) {
        // Static exchange rates
        if (from == CurrencyType.EURO && to == CurrencyType.DOLLAR) {
            return 1.1;
        } else if (from == CurrencyType.EURO && to == CurrencyType.INDIAN_RUPEE) {
            return 80;
        } else if (from == CurrencyType.DOLLAR && to == CurrencyType.EURO) {
            return 0.9;
        } else if (from == CurrencyType.DOLLAR && to == CurrencyType.INDIAN_RUPEE) {
            return 73;
        } else if (from == CurrencyType.INDIAN_RUPEE && to == CurrencyType.EURO) {
            return 0.0125;
        } else if (from == CurrencyType.INDIAN_RUPEE && to == CurrencyType.DOLLAR) {
            return 0.0137;
        } else {
            // Assuming 1:1 conversion for same currency type or unsupported pairs
            return 1;
        }
    }
}
