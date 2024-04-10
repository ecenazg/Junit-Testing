package com.clarity.business;

import com.client.junit.business.ClientBOImpl;

import com.client.junit.business.services.DifferentCurrenciesException;
import com.client.junit.model.*;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.Assert.*;
public class ProductSumTableTest {
    private ClientBOImpl clientBO;
    @Before
    public void setUp() {
        clientBO = new ClientBOImpl();
    }
    @Test
    public void testGetClientProductsSum_EmptyProductList() {
        Amount result = null;
        try {
            result = clientBO.getClientProductsSum(Collections.emptyList());
        } catch (DifferentCurrenciesException e) {
            throw new RuntimeException(e);
        }
        assertEquals(BigDecimal.ZERO, result.getValue());
        assertEquals(CurrencyType.EURO, result.getCurrency());
    }
    @Test
    public void testGetClientProductsSum_UniformCurrencyEuro() throws DifferentCurrenciesException {
        Product product1 = new ProductImpl(1, "Product1", ProductType.LOAN, new AmountImpl(new BigDecimal("100"), CurrencyType.EURO));
        Product product2 = new ProductImpl(2, "Product2", ProductType.CREDIT, new AmountImpl(new BigDecimal("200"), CurrencyType.EURO));
        Amount result = clientBO.getClientProductsSum(Arrays.asList(product1, product2));
        assertEquals(new BigDecimal("300"), result.getValue());
        assertEquals(CurrencyType.EURO, result.getCurrency());
    }
    @Test(expected = DifferentCurrenciesException.class)
    public void testGetClientProductsSum_MixedCurrencies() throws DifferentCurrenciesException {
        Product product1 = new ProductImpl(1, "Product1", ProductType.LOAN, new AmountImpl(new BigDecimal("100"), CurrencyType.EURO));
        Product product2 = new ProductImpl(2, "Product2", ProductType.CREDIT, new AmountImpl(new BigDecimal("100"), CurrencyType.DOLLAR));
        clientBO.getClientProductsSum(Arrays.asList(product1, product2));
    }
    @Test
    public void testGetClientProductsSum_UniformCurrencyDollar() throws DifferentCurrenciesException {
        Product product1 = new ProductImpl(1, "Product1", ProductType.LOAN, new AmountImpl(new BigDecimal("150"), CurrencyType.DOLLAR));
        Product product2 = new ProductImpl(2, "Product2", ProductType.CREDIT, new AmountImpl(new BigDecimal("150"), CurrencyType.DOLLAR));
        Amount result = clientBO.getClientProductsSum(Arrays.asList(product1, product2));
        assertEquals(new BigDecimal("300"), result.getValue());
        assertEquals(CurrencyType.DOLLAR, result.getCurrency());
    }
    @Test
    public void testGetClientProductsSum_SingleProduct() throws DifferentCurrenciesException {
        Product product = new ProductImpl(1, "SingleProduct", ProductType.LOAN, new AmountImpl(new BigDecimal("500"), CurrencyType.EURO));
        Amount result = clientBO.getClientProductsSum(Collections.singletonList(product));
        assertEquals(new BigDecimal("500"), result.getValue());
        assertEquals(CurrencyType.EURO, result.getCurrency());
    }
}

