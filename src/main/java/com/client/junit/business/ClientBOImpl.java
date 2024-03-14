package com.client.junit.business;

import java.math.BigDecimal;
import java.util.List;

import com.client.junit.business.services.DifferentCurrenciesException;
import com.client.junit.model.Amount;
import com.client.junit.model.AmountImpl;
import com.client.junit.model.CurrencyType;
import com.client.junit.model.Product;

public class ClientBOImpl implements ClientBO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.clarity.model.ClientBO#getClientProductsSum(java.util.List)
	 */
	@Override
	public Amount getClientProductsSum(List<Product> products)
			throws DifferentCurrenciesException {

		if (products.size() == 0)
			return new AmountImpl(BigDecimal.ZERO, CurrencyType.EURO);

		if (!isCurrencySameForAllProducts(products)) {
			throw new DifferentCurrenciesException();
		}

		BigDecimal productSum = calculateProductSum(products);

		CurrencyType firstProductCurrency = products.get(0).getAmount()
				.getCurrency();

		return new AmountImpl(productSum, firstProductCurrency);
	}

	private BigDecimal calculateProductSum(List<Product> products) {
		BigDecimal sum = BigDecimal.ZERO;
		// Calculate Sum of Products
		for (Product product : products) {
			sum = sum.add(product.getAmount().getValue());
		}
		return sum;
	}

	private boolean isCurrencySameForAllProducts(List<Product> products)
			throws DifferentCurrenciesException {

		CurrencyType firstProductCurrency = products.get(0).getAmount()
				.getCurrency();

		for (Product product : products) {
			boolean currencySameAsFirstProduct = product.getAmount()
					.getCurrency().equals(firstProductCurrency);
			if (!currencySameAsFirstProduct) {
				return false;
			}
		}

		return true;
	}
}