package com.client.junit.business.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BudgetTracker {

    private List<Transaction> transactions;

    public BudgetTracker() {
        this.transactions = new ArrayList<>();
    }

    public void addIncome(String source, BigDecimal amount) {
        Transaction income = new Transaction(TransactionType.INCOME, source, amount);
        transactions.add(income);
    }

    public void addExpense(String description, BigDecimal amount) {
        Transaction expense = new Transaction(TransactionType.EXPENSE, description, amount);
        transactions.add(expense);
    }

    public BigDecimal getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenses() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getCurrentBalance() {
        BigDecimal totalIncome = getTotalIncome();
        BigDecimal totalExpenses = getTotalExpenses();
        return totalIncome.subtract(totalExpenses);
    }

    // Inner class to represent transactions
    private static class Transaction {
        private TransactionType type;
        private String description;
        private BigDecimal amount;

        public Transaction(TransactionType type, String description, BigDecimal amount) {
            this.type = type;
            this.description = description;
            this.amount = amount;
        }

        public TransactionType getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }

        public BigDecimal getAmount() {
            return amount;
        }
    }

    // Enum to represent the type of transactions
    private enum TransactionType {
        INCOME, EXPENSE
    }
}