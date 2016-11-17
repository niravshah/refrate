package com.nns;

import com.sun.istack.internal.Nullable;

public interface FxPrice {
    double getBid();
    double getOffer();
    boolean isStale();
    boolean isNotStale();

    PriceSource getSource();
    @Nullable
    PriceProvider getProvider();
}
