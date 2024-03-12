package com.client.junit.business;

import java.util.List;

import com.client.junit.business.exception.DifferentCurrenciesException;
import com.client.junit.model.Amount;
import com.client.junit.model.Product;

public interface ClientBO {

	Amount getClientProductsSum(List<Product> products)
			throws DifferentCurrenciesException;

}