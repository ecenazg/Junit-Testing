package com.client.junit.model;

/**
 * Collateral Model Object.
 */
public class ProductImpl implements Product {

	private long id;

	private String name;

	private ProductType type;

	private Amount amount;

	public ProductImpl(long id, String name, ProductType type, Amount amount) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.amount = amount;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ProductType getType() {
		return type;
	}


	@Override
	public Amount getAmount() {
		return amount;
	}

	@Override
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
}
