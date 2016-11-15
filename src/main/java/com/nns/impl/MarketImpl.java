package com.nns.impl;

import com.nns.Market;
import com.nns.PriceProvider;
import com.nns.PriceSource;

public class MarketImpl implements Market {

    private PriceSource priceSource;
    private PriceProvider priceProvider;

    public MarketImpl(PriceSource priceSource, PriceProvider priceProvider) {
        this.priceSource = priceSource;
        this.priceProvider = priceProvider;
    }

    @Override
    public PriceSource getPriceSource() {
        return null;
    }

    @Override
    public PriceProvider getPriceProvider() {
        return null;
    }
}
