package com.client.junit.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Client Model API.
 */
public class ClientImpl implements Client {

	private long id;

	private String name;

	private ClientType type;

	private List<Product> products;

	private BigDecimal productAmount;

	public ClientImpl(long id, String name, ClientType type, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ClientType getType() {
		return type;
	}

	public void setType(ClientType type) {
		this.type = type;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public BigDecimal getProductAmount() {
		return productAmount;
	}

	@Override
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

}
