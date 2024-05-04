package com.clarity.business;

import com.client.junit.business.services.BudgetTracker;
import com.client.junit.business.services.UserService;
import com.client.junit.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private BudgetTracker budgetTracker;  // Mock interaction

    @BeforeEach
    public void setup() {
        // Assume we're interacting with BudgetTracker when creating a user
        doNothing().when(budgetTracker).addIncome(anyString(), any(BigDecimal.class));
    }

    @Test
    public void testCreateUser() {
        User user = userService.createUser("john_doe", "john.doe@example.com", "password123");
        assertNotNull(user);
        verify(budgetTracker, times(1)).addIncome(anyString(), any(BigDecimal.class));
    }

    // Additional tests for update, delete...
}
