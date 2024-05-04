package com.client.junit.model;

import java.math.BigDecimal;

public class Transaction {
    private String stockSymbol;
    private int numberOfShares;
    private BigDecimal pricePerShare;
    private TransactionType transactionType;

    String source;
    BigDecimal amount;


    public Transaction(TransactionType transactionType, String source, BigDecimal amount) {
        this.amount = amount;
        this.transactionType=transactionType;
        this.source = source;
    }

    public Transaction(String stockSymbol, int numberOfShares, BigDecimal pricePerShare, TransactionType transactionType) {
        this.stockSymbol = stockSymbol;
        this.numberOfShares = numberOfShares;
        this.pricePerShare = pricePerShare;
        this.transactionType = transactionType;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public BigDecimal getPricePerShare() {
        return pricePerShare;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}