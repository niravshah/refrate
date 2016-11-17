package com.nns.impl.builders;

import com.nns.Market;
import com.nns.PriceProvider;
import com.nns.PriceSource;
import com.nns.impl.MarketImpl;

public class MarketBuilder {

    private PriceProvider priceProvider;
    private PriceSource priceSource;

    public static MarketBuilder marketBuilder() {
        return new MarketBuilder();
    }

    public Market build() {
        return new MarketImpl(priceSource, priceProvider);
    }

    public MarketBuilder priceSource(PriceSource priceSource) {
        this.priceSource = priceSource;
        return this;
    }

    public MarketBuilder priceProvider(PriceProvider priceProvider) {
        this.priceProvider = priceProvider;
        return this;
    }
}
