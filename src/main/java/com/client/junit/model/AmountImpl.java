package com.client.junit.model;

import java.math.BigDecimal;

public class AmountImpl implements Amount {

	BigDecimal value;
	CurrencyType currency;

	public AmountImpl(BigDecimal value, CurrencyType currency) {
		super();
		this.value = value;
		this.currency = currency;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}

	@Override
	public CurrencyType getCurrency() {
		return currency;
	}

}
