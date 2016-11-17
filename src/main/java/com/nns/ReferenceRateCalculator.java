package com.nns;

import com.sun.istack.internal.Nullable;

import java.util.List;
import java.util.Map;

public interface ReferenceRateCalculator {
    void onConfiguration(Configuration configuration);
    void onFxPrice(FxPrice fxPrice);
    FxPrice calculate();

    List<Market> getMarketsConfigured();

    Map<Market, FxPrice> getMarketFxPriceList();

    interface Configuration {
        int getSize();
        PriceSource getSource(int index);
        @Nullable
        PriceProvider getProvider(int index);
    }
}
