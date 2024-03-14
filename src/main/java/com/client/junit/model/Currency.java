package com.client.junit.model;

public class Currency {

    public CurrencyType type;
    public Amount amount;

    public Currency(CurrencyType type, Amount amount) {
        this.type = type;
        this.amount = amount;
    }

    public Currency() {
    }

    public CurrencyType getType() {
        return type;
    }

    public void setType(CurrencyType type) {
        this.type = type;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
