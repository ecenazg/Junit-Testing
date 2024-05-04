package com.client.junit.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialGoal {
    private String name;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean achieved;

    // Constructor
    public FinancialGoal(String name, BigDecimal targetAmount, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = BigDecimal.ZERO;
        this.startDate = startDate;
        this.endDate = endDate;
        this.achieved = false;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    // Method to update current amount based on user's progress
    public void updateCurrentAmount(BigDecimal amountToAdd) {
        this.currentAmount = this.currentAmount.add(amountToAdd);
        if (this.currentAmount.compareTo(this.targetAmount) >= 0) {
            this.achieved = true;
        }
    }

    // Method to calculate remaining days until the end date of the goal
    public long remainingDays() {
        return LocalDate.now().until(endDate).getDays();
    }

    // Method to calculate the percentage of completion of the goal
    public double completionPercentage() {
        return (currentAmount.doubleValue() / targetAmount.doubleValue()) * 100.0;
    }
}