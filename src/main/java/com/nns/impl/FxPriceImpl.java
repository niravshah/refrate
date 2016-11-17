package com.nns.impl;

import com.nns.FxPrice;
import com.nns.PriceProvider;
import com.nns.PriceSource;

import java.util.Objects;

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
    public boolean isNotStale() {
        return !stale;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FxPriceImpl)) {
            return false;
        }
        FxPriceImpl fxPrice = (FxPriceImpl) o;
        return Double.compare(fxPrice.getBid(), getBid()) == 0 &&
                Double.compare(fxPrice.getOffer(), getOffer()) == 0 &&
                isStale() == fxPrice.isStale() &&
                getSource() == fxPrice.getSource() &&
                getProvider() == fxPrice.getProvider();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBid(), getOffer(), getSource(), getProvider(), isStale());
    }

    @Override
    public int compareTo(Object o) {
        FxPriceImpl other = (FxPriceImpl) o;
        double thisMid = (this.getBid() + this.getOffer());
        double otherMid = (other.getBid() + other.getOffer());
        if (thisMid == otherMid) {
            return 0;
        }
        return thisMid < otherMid ? -1 : 1;
    }
}
