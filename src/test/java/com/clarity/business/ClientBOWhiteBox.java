package com.clarity.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.client.junit.model.*;
import org.junit.Before;
import org.junit.Test;
import com.client.junit.business.ClientBO;
import com.client.junit.business.ClientBOImpl;
import com.client.junit.business.services.DifferentCurrenciesException;
/*
Cyclomatic Complexity (CC)
Cyclomatic complexity is a metric used in software development to indicate the complexity of a program. It is calculated using the formula CC = E - N + 2P, where:

E = the number of edges in the flow graph.
N = the number of nodes in the flow graph.
P = the number of connected components (in most cases, P=1 for a single program).
For getClientProductsSum, we can construct a simplified flow graph based on the logic:

Start (node)
Check if products list is empty (node)
Check if all products have the same currency (node)
Calculate sum of products (node)
Return amount (node)
Assuming a sequential flow between these nodes without additional branching, we have:

E = 4 (edges between the 5 nodes)
N = 5 (nodes)
P = 1 (a single connected component)
Substituting these values into the formula gives us: CC = 4 - 5 + 2(1) = 1. However, this doesn't consider the internal branching logic within steps 2 and 3, which actually introduces additional paths (edges) for branching, significantly increasing E.

Considering the branching:

If list is empty
If currencies are inconsistent
Normal flow (currencies are consistent and list is not empty)
This would add at least 2 more edges for the checks, potentially more based on the internal logic of isCurrencySameForAllProducts. Without an explicit flowchart, an accurate manual calculation is challenging, but your method likely has a CC of at least 4, given the description of needing 4 paths for the assignment.

Basis Paths
Given the above understanding, the basis paths derived from your method's logic are:

Empty Product List: The product list is empty.
Inconsistent Currencies: The product list contains products with inconsistent currencies.
Consistent Currencies: The product list contains products with consistent currencies, and their amounts are summed.
*/
public class ClientBOWhiteBox {
    private ClientImpl client;
    private Product product1;
    private Product product2;
    private ClientBO clientBO = new ClientBOImpl();
    @Before
    public void setUp() {
        product1 = new ProductImpl(1, "Product1", ProductType.LOAN, new AmountImpl(BigDecimal.valueOf(100), CurrencyType.EURO));
        product2 = new ProductImpl(2, "Product2", ProductType.BANK_GUARANTEE, new AmountImpl(BigDecimal.valueOf(200), CurrencyType.EURO));
        List<Product> products = Arrays.asList(product1, product2);
        client = new ClientImpl(123, "ClientName", ClientType.PRIVATE, products);
    }
    @Test
    public void testEmptyProductList() {
        try {
            assertTrue(clientBO.getClientProductsSum(new ArrayList<>()).getValue().compareTo(BigDecimal.ZERO) == 0);
        } catch (DifferentCurrenciesException e) {
            throw new RuntimeException(e);
        }
    }
    @Test(expected = DifferentCurrenciesException.class)
    public void testInconsistentCurrencies() throws DifferentCurrenciesException {
        // Setup products with different currencies
        Product product1 = new ProductImpl(1, "Product1", ProductType.LOAN, new AmountImpl(BigDecimal.valueOf(100), CurrencyType.EURO));
        Product product2 = new ProductImpl(2, "Product2", ProductType.BANK_GUARANTEE, new AmountImpl(BigDecimal.valueOf(200), CurrencyType.DOLLAR));
        clientBO.getClientProductsSum(Arrays.asList(product1, product2)); // This should throw DifferentCurrenciesException
    }
    @Test
    public void testConsistentCurrenciesSum() {
        try {
            BigDecimal expected = new BigDecimal("300.00");
            BigDecimal actual = clientBO.getClientProductsSum(Arrays.asList(product1, product2)).getValue();
            assertTrue(expected.compareTo(actual) == 0);
        } catch (DifferentCurrenciesException e) {
            throw new RuntimeException(e);
        }
    }
}
