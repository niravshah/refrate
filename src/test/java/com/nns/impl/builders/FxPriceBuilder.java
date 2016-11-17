package com.nns.impl.builders;

import com.nns.FxPrice;
import com.nns.PriceProvider;
import com.nns.PriceSource;
import com.nns.impl.FxPriceImpl;

public class FxPriceBuilder {

    private double bid;
    private double offer;
    private PriceSource source;
    private PriceProvider provider;
    private boolean stale;

    public static FxPriceBuilder fxPriceBuilder() {
        return new FxPriceBuilder();
    }

    public FxPrice build() {
        return new FxPriceImpl(bid, offer, source, provider, stale);
    }

    public FxPriceBuilder bid(double bid) {
        this.bid = bid;
        return this;
    }

    public FxPriceBuilder offer(double offer) {
        this.offer = offer;
        return this;
    }

    public FxPriceBuilder source(PriceSource source) {
        this.source = source;
        return this;
    }

    public FxPriceBuilder provider(PriceProvider priceProvider) {
        this.provider = priceProvider;
        return this;
    }

    public FxPriceBuilder stale(boolean stale) {
        this.stale = stale;
        return this;
    }
}

