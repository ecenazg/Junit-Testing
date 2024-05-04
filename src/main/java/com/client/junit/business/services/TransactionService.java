package com.client.junit.business.services;

import java.math.BigDecimal;

public class TransactionService {
    private StockTransaction stockTransaction;
    private BudgetTracker budgetTracker;

    public TransactionService() {
        this.stockTransaction = new StockTransaction();
        this.budgetTracker = new BudgetTracker();
    }

    // Delegate methods for stock transactions
    public void buyStock(String stockSymbol, int numberOfShares, BigDecimal pricePerShare) {
        stockTransaction.buyStock(stockSymbol, numberOfShares, pricePerShare);
    }

    public void sellStock(String stockSymbol, int numberOfShares, BigDecimal pricePerShare) {
        stockTransaction.sellStock(stockSymbol, numberOfShares, pricePerShare);
    }

    // Delegate methods for budget tracking
    public void addIncome(String source, BigDecimal amount) {
        budgetTracker.addIncome(source, amount);
    }

    public void addExpense(String description, BigDecimal amount) {
        budgetTracker.addExpense(description, amount);
    }

    // Combined or additional functionalities can be added here
}
