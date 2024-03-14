package com.clarity.business;

import static org.junit.Assert.assertEquals;
import com.client.junit.model.Currency;
import com.client.junit.model.CurrencyType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import com.client.junit.business.services.StaticCurrencyConverter;
import com.client.junit.business.ClientBO;
import com.client.junit.business.ClientBOImpl;
import com.client.junit.business.services.DifferentCurrenciesException;
import com.client.junit.model.Amount;
import com.client.junit.model.AmountImpl;
import com.client.junit.model.CurrencyType;
import com.client.junit.model.Product;
import com.client.junit.model.ProductImpl;
import com.client.junit.model.ProductType;

public class ClientBOTest {

	private ClientBO clientBO = new ClientBOImpl();
	private StaticCurrencyConverter converter = new StaticCurrencyConverter();
	@Test
	public void testClientProductSum() throws DifferentCurrenciesException {

		List<Product> products = new ArrayList<Product>();

		products.add(new ProductImpl(100, "Product 15",
				ProductType.BANK_GUARANTEE, new AmountImpl(
						new BigDecimal("5.0"), CurrencyType.EURO)));

		products.add(new ProductImpl(120, "Product 20",
				ProductType.BANK_GUARANTEE, new AmountImpl(
						new BigDecimal("6.0"), CurrencyType.EURO)));

		Amount temp = clientBO.getClientProductsSum(products);

		assertEquals(CurrencyType.EURO, temp.getCurrency());
		assertEquals(new BigDecimal("11.0"), temp.getValue());
		//TODO: add more 2 assertion
	}

	@Test(expected = DifferentCurrenciesException.class)
	public void testClientProductSum1() throws DifferentCurrenciesException {

		List<Product> products = new ArrayList<Product>();

		products.add(new ProductImpl(100, "Product 15",
				ProductType.BANK_GUARANTEE, new AmountImpl(
						new BigDecimal("5.0"), CurrencyType.INDIAN_RUPEE)));

		products.add(new ProductImpl(120, "Product 20",
				ProductType.BANK_GUARANTEE, new AmountImpl(
						new BigDecimal("6.0"), CurrencyType.EURO)));
		//TODO: add more 2 assertion
		@SuppressWarnings("unused")
		Amount temp = null;

		temp = clientBO.getClientProductsSum(products);
	}

	@Test
	public void testClientProductSum2() {

		List<Product> products = new ArrayList<Product>();

		Amount temp = null;

		try {
			temp = clientBO.getClientProductsSum(products);
		} catch (DifferentCurrenciesException e) {
		}
		assertEquals(CurrencyType.EURO, temp.getCurrency());
		assertEquals(BigDecimal.ZERO, temp.getValue());
	}



}