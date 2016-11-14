package com.nns.impl;

import com.nns.FxPrice;
import com.nns.PriceProvider;
import com.nns.PriceSource;

public class FxPriceImpl implements FxPrice, Comparable {


    private double bid;
    private double offer;
    private PriceSource source;
    private PriceProvider provider;
    private boolean stale;

    public FxPriceImpl(double bid, double offer, PriceSource source, PriceProvider provider, boolean stale) {
        this.bid = bid;
        this.offer = offer;
        this.source = source;
        this.provider = provider;
        this.stale = stale;
    }

    @Override
    public double getBid() {
        return bid;
    }

    @Override
    public double getOffer() {
        return offer;
    }

    @Override
    public boolean isStale() {
        return stale;
    }

    @Override
    public PriceSource getSource() {
        return source;
    }

    @Override
    public PriceProvider getProvider() {
        return provider;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
