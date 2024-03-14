package com.clarity.business;

import com.client.junit.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ClientTest {
    private ClientImpl client;
    private Product product1;
    private Product product2;

    @Before
    public void setUp() {
        product1 = new ProductImpl(1, "Product1", ProductType.LOAN, new AmountImpl(BigDecimal.valueOf(100), CurrencyType.EURO));
        product2 = new ProductImpl(2, "Product2", ProductType.BANK_GUARANTEE, new AmountImpl(BigDecimal.valueOf(200), CurrencyType.EURO));
        List<Product> products = Arrays.asList(product1, product2);
        client = new ClientImpl(123, "ClientName", ClientType.PRIVATE, products);
    }

    @Test
    public void testGetId() {
        assertEquals(123, client.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("ClientName", client.getName());
    }

    @Test
    public void testGetType() {
        assertEquals(ClientType.PRIVATE, client.getType());
    }

    @Test
    public void testGetAndSetProducts() {
        assertEquals(2, client.getProducts().size());
        assertTrue(client.getProducts().contains(product1));
        assertTrue(client.getProducts().contains(product2));

        Product product3 = new ProductImpl(3, "Product3", ProductType.CREDIT, new AmountImpl(BigDecimal.valueOf(300), CurrencyType.EURO));
        client.setProducts(Arrays.asList(product3));
        assertEquals(1, client.getProducts().size());
        assertTrue(client.getProducts().contains(product3));
    }

    @Test
    public void testGetAndSetProductAmount() {
        client.setProductAmount(BigDecimal.valueOf(500));
        assertEquals(0, BigDecimal.valueOf(500).compareTo(client.getProductAmount()));
    }
}
