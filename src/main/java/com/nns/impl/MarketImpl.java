package com.nns.impl;

import com.nns.Market;
import com.nns.PriceProvider;
import com.nns.PriceSource;

import java.util.Objects;

public class MarketImpl implements Market {

    private PriceSource priceSource;
    private PriceProvider priceProvider;

    public MarketImpl(PriceSource priceSource, PriceProvider priceProvider) {
        this.priceSource = priceSource;
        this.priceProvider = priceProvider;
    }

    @Override
    public PriceSource getPriceSource() {
        return priceSource;
    }

    @Override
    public PriceProvider getPriceProvider() {
        return priceProvider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarketImpl)) {
            return false;
        }
        MarketImpl market = (MarketImpl) o;
        return getPriceSource() == market.getPriceSource() &&
                getPriceProvider() == market.getPriceProvider();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPriceSource(), getPriceProvider());
    }

    @Override
    public String toString() {
        return "MarketImpl{" +
                "priceSource=" + priceSource +
                ", priceProvider=" + priceProvider +
                '}';
    }
}
