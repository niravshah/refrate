package com.nns;

import com.sun.istack.internal.Nullable;

public interface FxPrice {
    double getBid();
    double getOffer();
    boolean isStale();
    PriceSource getSource();
    @Nullable
    PriceProvider getProvider();
}
