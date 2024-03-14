package com.client.junit.model;
public enum CurrencyType {

	EURO("EUR"), DOLLAR("USD"), INDIAN_RUPEE("INR");

	private final String textValue;

	CurrencyType(final String textValue) {
		this.textValue = textValue;
	}

	@Override
	public String toString() {
		return textValue;
	}
}
