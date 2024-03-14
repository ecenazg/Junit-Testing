package com.client.junit.model;

import java.math.BigDecimal;

public interface Amount {
	BigDecimal getValue();

	CurrencyType getCurrency();
}
