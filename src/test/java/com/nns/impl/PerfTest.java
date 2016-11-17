package com.nns.impl;

import com.nns.PriceProvider;
import com.nns.PriceSource;
import org.testng.annotations.DataProvider;

public class PerfTest {

    @DataProvider
    public Object[][] getMarkets() {
        return new Object[][]{{new MarketImpl(PriceSource.SOURCE1, PriceProvider.PROVIDER1)}};
    }

}
