package com.client.junit.model;

public class Currency {

    public CurrencyType type;
    public Amount amount;

    public Currency(CurrencyType type, Amount amount) {
        this.type = type;
        this.amount = amount;
    }

    public CurrencyType getType() {
        return type;
    }

    public Amount getAmount() {
        return amount;
    }


}
