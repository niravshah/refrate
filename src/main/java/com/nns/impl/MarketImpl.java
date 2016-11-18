package com.nns.impl;

import com.nns.Market;
import com.nns.PriceProvider;
import com.nns.PriceSource;
import java.util.Objects;

public class MarketImpl implements Market {


    private PriceSource source;


    private PriceProvider provider;

    public MarketImpl(PriceSource priceSource, PriceProvider priceProvider) {
        this.source = priceSource;
        this.provider = priceProvider;
    }

    public PriceSource getSource() {
        return source;
    }

    public PriceProvider getProvider() {
        return provider;
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
        return getSource() == market.getSource() &&
                getProvider() == market.getProvider();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getProvider());
    }

    @Override
    public String toString() {
        return "MarketImpl{" +
                "source=" + source +
                ", provider=" + provider +
                '}';
    }
}
