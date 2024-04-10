package com.clarity.business;

import static org.junit.Assert.*;

import com.client.junit.business.services.BudgetTracker;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
/*
Cyclomatic Complexity (CC)
Given that the BudgetTracker class methods mostly add transactions or aggregate values without conditional branches
(except for the filters in aggregation methods),
each primary method (addIncome, addExpense, getTotalIncome, getTotalExpenses, and getCurrentBalance)
can be considered to have a CC of 1,
indicating a straightforward, single-path flow. However, when considering the stream operations with filters, we might consider each decision point.
Since these are simple filters without complex branching logic, we'll maintain the CC of 1 for simplicity,
acknowledging that real-world applications might demand a more nuanced analysis.

Basis Paths
Given the structure of BudgetTracker, the basis paths to test are straightforward:

Adding Income: Verify that adding an income transaction correctly affects totals.
Adding Expense: Verify that adding an expense transaction correctly affects totals.
Calculating Total Income: Ensure the calculation only sums up income transactions.
Calculating Total Expenses: Ensure the calculation only sums up expense transactions.
Calculating Current Balance: Verify the balance is correctly computed as total income minus total expenses.
*/
public class BudgetTrackerWhiteBox {
    private BudgetTracker budgetTracker;
    @Before
    public void setUp() {
        budgetTracker = new BudgetTracker();
    }
    @Test
    public void testAddingIncomeIncreasesTotalIncome() {
        BigDecimal initialTotalIncome = budgetTracker.getTotalIncome();
        budgetTracker.addIncome("Freelance", new BigDecimal("500"));
        assertEquals(initialTotalIncome.add(new BigDecimal("500")), budgetTracker.getTotalIncome());
    }
    @Test
    public void testAddingExpenseIncreasesTotalExpenses() {
        BigDecimal initialTotalExpenses = budgetTracker.getTotalExpenses();
        budgetTracker.addExpense("Groceries", new BigDecimal("150"));
        assertEquals(initialTotalExpenses.add(new BigDecimal("150")), budgetTracker.getTotalExpenses());
    }
    @Test
    public void testTotalIncomeCalculation() {
        budgetTracker.addIncome("Salary", new BigDecimal("2000"));
        budgetTracker.addIncome("Bonus", new BigDecimal("300"));
        assertEquals(new BigDecimal("2300"), budgetTracker.getTotalIncome());
    }
    @Test
    public void testTotalExpensesCalculation() {
        budgetTracker.addExpense("Rent", new BigDecimal("800"));
        budgetTracker.addExpense("Utilities", new BigDecimal("200"));
        assertEquals(new BigDecimal("1000"), budgetTracker.getTotalExpenses());
    }
    @Test
    public void testCurrentBalanceCalculation() {
        budgetTracker.addIncome("Salary", new BigDecimal("2000"));
        budgetTracker.addExpense("Rent", new BigDecimal("800"));
        assertEquals(new BigDecimal("1200"), budgetTracker.getCurrentBalance());
    }
}
