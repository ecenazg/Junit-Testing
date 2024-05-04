package com.client.junit.business.services;

import com.client.junit.model.Transaction;
import com.client.junit.model.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BudgetTracker {

    private List<Transaction> transactions;

    public BudgetTracker() {
        this.transactions = new ArrayList<>();
    }

    public void addIncome(String source, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Income amount cannot be negative");
        }
        transactions.add(new Transaction(TransactionType.INCOME, source, amount));
    }

    public void addExpense(String description, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Expense amount cannot be negative");
        }
        transactions.add(new Transaction(TransactionType.EXPENSE, description, amount));
    }

    public BigDecimal getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.INCOME)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenses() {
        return transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getCurrentBalance() {
        BigDecimal totalIncome = getTotalIncome();
        BigDecimal totalExpenses = getTotalExpenses();
        return totalIncome.subtract(totalExpenses);
    }

}