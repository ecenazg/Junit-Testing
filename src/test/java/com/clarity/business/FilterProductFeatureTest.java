package com.clarity.business;
import com.client.junit.model.*;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// This class contains 2 Cyclomatic Complexity (CC) For the filterProductsByType and filterProductsByAmountRange methods both of them are 1.
/*For the filterProductsByType and filterProductsByAmountRange methods;

Cyclomatic Complexity (CC)
Both methods involve a single decision point based on a filter condition.
Each method processes a list and applies a condition without branching logic within the methods themselves.
Therefore, each method has a cyclomatic complexity of 1, indicating a single linear path through each method without branches.
However, when considering the usage of these methods in testing,
it's the conditions within the filter that we're interested in testing -
the presence or absence of products that meet the criteria.

Basis Paths
Given the simplicity of these methods, the primary paths to test are:

For filterProductsByType:

A. Products list contains products of the specified type.
B. Products list does not contain any products of the specified type.

For filterProductsByAmountRange:

A. Products list contains products within the specified amount range.
B. Products list does not contain any products within the specified amount range.
*/
public class FilterProductFeatureTest {
    private ClientImpl client;
    private Product product1, product2, product3;
    @Before
    public void setUp() {
        product1 = new ProductImpl(1, "Product 1", ProductType.LOAN, new AmountImpl(new BigDecimal("100"), CurrencyType.EURO));
        product2 = new ProductImpl(2, "Product 2", ProductType.CREDIT, new AmountImpl(new BigDecimal("200"), CurrencyType.EURO));
        product3 = new ProductImpl(3, "Product 3", ProductType.LOAN, new AmountImpl(new BigDecimal("300"), CurrencyType.EURO));
        client = new ClientImpl(123, "Test Client", ClientType.BUSINESS, Arrays.asList(product1, product2, product3));
    }
    // filterProductsByType tests
    @Test
    public void filterProductsByType_EmptyList() {
        client.setProducts(new ArrayList<>());
        assertTrue(client.filterProductsByType(ProductType.LOAN).isEmpty());
    }
    @Test
    public void filterProductsByType_NoMatchingType() {
        assertTrue(client.filterProductsByType(ProductType.BANK_GUARANTEE).isEmpty());
    }
    @Test
    public void filterProductsByType_WithMatchingType() {
        List<Product> filtered = client.filterProductsByType(ProductType.LOAN);
        assertEquals(2, filtered.size());
        assertTrue(filtered.containsAll(Arrays.asList(product1, product3)));
    }
    // filterProductsByAmountRange tests
    @Test
    public void filterProductsByAmountRange_EmptyList() {
        client.setProducts(new ArrayList<>());
        assertTrue(client.filterProductsByAmountRange(new BigDecimal("100"), new BigDecimal("200")).isEmpty());
    }
    @Test
    public void filterProductsByAmountRange_NoMatchingRange() {
        assertTrue(client.filterProductsByAmountRange(new BigDecimal("400"), new BigDecimal("500")).isEmpty());
    }
    @Test
    public void filterProductsByAmountRange_WithMatchingRange() {
        List<Product> filtered = client.filterProductsByAmountRange(new BigDecimal("150"), new BigDecimal("250"));
        assertEquals(1, filtered.size());
        assertTrue(filtered.contains(product2));
    }
}
