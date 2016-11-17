package com.nns;

public interface Market {

    PriceSource getSource();
    PriceProvider getProvider();
}
