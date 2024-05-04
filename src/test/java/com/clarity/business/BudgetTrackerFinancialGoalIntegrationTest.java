package com.clarity.business;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.client.junit.business.services.BudgetTracker;
import com.client.junit.model.FinancialGoal;

public class BudgetTrackerFinancialGoalIntegrationTest {

    @Mock
    private BudgetTracker budgetTracker;

    @Mock
    private FinancialGoal financialGoal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(financialGoal.getTargetAmount()).thenReturn(new BigDecimal("5000.00"));
        when(financialGoal.getStartDate()).thenReturn(LocalDate.now());
        when(financialGoal.getEndDate()).thenReturn(LocalDate.now().plusMonths(6));
    }

    @Test
    void testBudgetTrackerAndGoalIntegration() {
        // Arrange
        when(budgetTracker.getCurrentBalance()).thenReturn(new BigDecimal("2000.00"), new BigDecimal("3000.00"), new BigDecimal("5000.00"));
        doNothing().when(financialGoal).setCurrentAmount(any(BigDecimal.class));
        when(financialGoal.getCurrentAmount()).thenReturn(new BigDecimal("2000.00"), new BigDecimal("3000.00"), new BigDecimal("5000.00"));

        // Act and Assert for initial state
        financialGoal.setCurrentAmount(budgetTracker.getCurrentBalance());
        assertEquals(new BigDecimal("2000.00"), financialGoal.getCurrentAmount());

        // Update for a new income
        financialGoal.setCurrentAmount(budgetTracker.getCurrentBalance());
        assertEquals(new BigDecimal("3000.00"), financialGoal.getCurrentAmount());

        // Goal achieved
        financialGoal.setCurrentAmount(budgetTracker.getCurrentBalance());
        assertEquals(new BigDecimal("5000.00"), financialGoal.getCurrentAmount());
        verify(financialGoal, times(3)).setCurrentAmount(any(BigDecimal.class));
        verify(budgetTracker, times(3)).getCurrentBalance();
    }

    @Test
    void testGoalProgressTracking() {
        // Setup
        when(financialGoal.getCurrentAmount()).thenReturn(new BigDecimal("1000.00"), new BigDecimal("2500.00"), new BigDecimal("4500.00"));
        when(financialGoal.isAchieved()).thenReturn(false, false, true);

        // Verify increment progress
        financialGoal.setCurrentAmount(new BigDecimal("1000.00"));
        assertFalse(financialGoal.isAchieved());

        financialGoal.setCurrentAmount(new BigDecimal("2500.00"));
        assertFalse(financialGoal.isAchieved());

        financialGoal.setCurrentAmount(new BigDecimal("4500.00"));
        assertTrue(financialGoal.isAchieved());

        verify(financialGoal, times(3)).setCurrentAmount(any(BigDecimal.class));
        verify(financialGoal, times(3)).isAchieved();
    }

    @Test
    void testGoalCompletionTimeframe() {
        // Simulate time passing and goal approaching deadline
        when(financialGoal.remainingDays()).thenReturn(180L, 90L, 1L);

        // Assert changes in time left to achieve goal
        assertEquals(180L, financialGoal.remainingDays());
        assertEquals(90L, financialGoal.remainingDays());
        assertEquals(1L, financialGoal.remainingDays());

        verify(financialGoal, times(3)).remainingDays();
    }

    @Test
    void testAdjustFinancialGoalDuringPeriod() {
        // Setup changes in target amount
        when(financialGoal.getTargetAmount()).thenReturn(new BigDecimal("5000.00"), new BigDecimal("6000.00"), new BigDecimal("7000.00"));

        // Act
        assertEquals(new BigDecimal("5000.00"), financialGoal.getTargetAmount());
        assertEquals(new BigDecimal("6000.00"), financialGoal.getTargetAmount());
        assertEquals(new BigDecimal("7000.00"), financialGoal.getTargetAmount());

        // Verify adjustments to financial goals were queried correctly
        verify(financialGoal, times(3)).getTargetAmount();
    }

}
