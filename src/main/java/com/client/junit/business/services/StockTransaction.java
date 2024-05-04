package com.client.junit.business.services;

import com.client.junit.model.Transaction;
import com.client.junit.model.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StockTransaction {
    private List<Transaction> transactions;

    public StockTransaction() {
        this.transactions = new ArrayList<>();
    }

    public void buyStock(String stockSymbol, int numberOfShares, BigDecimal pricePerShare) {
        Transaction transaction = new Transaction(stockSymbol, numberOfShares, pricePerShare, TransactionType.BUY);
        transactions.add(transaction);
    }

    public void sellStock(String stockSymbol, int numberOfShares, BigDecimal pricePerShare) {
        Transaction transaction = new Transaction(stockSymbol, numberOfShares, pricePerShare, TransactionType.SELL);
        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public BigDecimal calculatePortfolioValue() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            BigDecimal transactionValue = transaction.getPricePerShare().multiply(BigDecimal.valueOf(transaction.getNumberOfShares()));
            if (transaction.getTransactionType() == TransactionType.BUY) {
                total = total.add(transactionValue);
            } else if (transaction.getTransactionType() == TransactionType.SELL) {
                total = total.subtract(transactionValue);
            }
        }
        return total;
    }
}
