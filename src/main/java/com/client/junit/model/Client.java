package com.client.junit.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Client Model API.
 */
public interface Client {

	long getId();

	String getName();

	Enum<?> getType();

	List<Product> getProducts();

	void setProductAmount(BigDecimal productAmount);

	BigDecimal getProductAmount();

}
