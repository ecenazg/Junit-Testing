package com.clarity.business;
import com.client.junit.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
 /*Cyclomatic Complexity (CC) = 1
Cyclomatic complexity measures the number of linearly independent paths through the program. For ClientImpl, each method is simple, mostly getters and setters, which don't add complexity regarding decision points. Therefore, for each method, the CC is 1, as there are no conditional branches.

Given that, let's outline the test paths for methods that do more than simply return or set a value, such as the constructor and any method involving logic beyond direct assignment or return. In this case, it seems all methods are straightforward, but for completeness, we'll consider the potential paths involved in the setting and getting of values.

Basis Paths
Given the structure of ClientImpl, the basis paths would include:

Constructor: Instantiates a ClientImpl object with specified id, name, type, and products.
setId: Sets the client's ID.
setName: Sets the client's name.
setType: Sets the client's type.
setProducts: Sets the client's products.
setProductAmount: Sets the client's product amount.

  */
public class ClientWhiteBox {
    private ClientImpl client;
    private Product product1;
    @Before
    public void setUp() {
        product1 = new ProductImpl(1, "Product1", ProductType.LOAN, new AmountImpl(BigDecimal.valueOf(100), CurrencyType.EURO));
        client = new ClientImpl(123, "TestClient", ClientType.PRIVATE, Collections.singletonList(product1));
    }
    @Test
    public void testClientConstructor() {
        assertNotNull(client);
        assertEquals(123, client.getId());
        assertEquals("TestClient", client.getName());
        assertEquals(ClientType.PRIVATE, client.getType());
        assertEquals(1, client.getProducts().size());
    }
    @Test
    public void testSetId() {
        client.setId(456);
        assertEquals(456, client.getId());
    }
    @Test
    public void testSetName() {
        client.setName("NewName");
        assertEquals("NewName", client.getName());
    }
    @Test
    public void testSetType() {
        client.setType(ClientType.BUSINESS);
        assertEquals(ClientType.BUSINESS, client.getType());
    }
    @Test
    public void testSetProducts() {
        Product product2 = new ProductImpl(2, "Product2", ProductType.CREDIT, new AmountImpl(BigDecimal.valueOf(200), CurrencyType.EURO));
        client.setProducts(Arrays.asList(product1, product2));
        assertEquals(2, client.getProducts().size());
    }
    @Test
    public void testSetProductAmount() {
        client.setProductAmount(new BigDecimal("500.00"));
        assertEquals(0, new BigDecimal("500.00").compareTo(client.getProductAmount()));
    }
}
