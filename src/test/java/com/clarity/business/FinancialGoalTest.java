package com.clarity.business;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.client.junit.model.FinancialGoal;
public class FinancialGoalTest {

    private FinancialGoal financialGoal;

    @BeforeEach
    void setUp() {
        // Initialize FinancialGoal with a start date of today and an end date six months from today.
        financialGoal = new FinancialGoal("Save for Vacation", new BigDecimal("10000"), LocalDate.now(), LocalDate.now().plusMonths(6));
    }

    @Test
    void testInitialValues() {
        // Assert initial values are set correctly.
        assertEquals("Save for Vacation", financialGoal.getName());
        assertEquals(new BigDecimal("10000"), financialGoal.getTargetAmount());
        assertEquals(BigDecimal.ZERO, financialGoal.getCurrentAmount());
        assertFalse(financialGoal.isAchieved());
    }

    @Test
    void testUpdateCurrentAmount() {
        // Adding an amount less than the target
        financialGoal.updateCurrentAmount(new BigDecimal("5000"));
        assertEquals(new BigDecimal("5000"), financialGoal.getCurrentAmount());
        assertFalse(financialGoal.isAchieved());

        // Adding another amount making the total equal to the target
        financialGoal.updateCurrentAmount(new BigDecimal("5000"));
        assertEquals(new BigDecimal("10000"), financialGoal.getCurrentAmount());
        assertTrue(financialGoal.isAchieved());
    }

    @Test
    void testUpdateCurrentAmountExceedsTarget() {
        // Test updating the current amount to exceed the target
        financialGoal.updateCurrentAmount(new BigDecimal("11000"));
        assertEquals(new BigDecimal("11000"), financialGoal.getCurrentAmount());
        assertTrue(financialGoal.isAchieved(), "Goal should be marked as achieved when current amount exceeds the target");
    }

    @Test
    void testCompletionPercentage() {
        financialGoal.updateCurrentAmount(new BigDecimal("2500"));
        assertEquals(25.0, financialGoal.completionPercentage(), 0.1, "Completion percentage should be correct");

        financialGoal.updateCurrentAmount(new BigDecimal("2500")); // Total now 5000
        assertEquals(50.0, financialGoal.completionPercentage(), 0.1, "Completion percentage should be updated correctly");
    }

    @Test
    void testSetters() {
        // Test setters
        financialGoal.setName("New Goal Name");
        financialGoal.setTargetAmount(new BigDecimal("20000"));
        financialGoal.setStartDate(LocalDate.now().plusDays(1));
        financialGoal.setEndDate(LocalDate.now().plusYears(1));

        assertEquals("New Goal Name", financialGoal.getName());
        assertEquals(new BigDecimal("20000"), financialGoal.getTargetAmount());
        assertEquals(LocalDate.now().plusDays(1), financialGoal.getStartDate());
        assertEquals(LocalDate.now().plusYears(1), financialGoal.getEndDate());
    }
}
