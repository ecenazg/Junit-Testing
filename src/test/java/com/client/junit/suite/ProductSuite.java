package com.client.junit.suite;

import com.clarity.business.ClientTest;
import com.clarity.business.CurrencyConverterTest;
import com.client.junit.helper.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({QuickBeforeAfterTest.class, StringHelperParameterizedTest.class, ClientTest.class})
public class ProductSuite {

}
