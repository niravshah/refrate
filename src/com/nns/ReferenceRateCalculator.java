package com.nns;

import com.sun.istack.internal.Nullable;

public interface ReferenceRateCalculator {
    void onConfiguration(Configuration configuration);
    void onFxPrice(FxPrice fxPrice);
    FxPrice calculate();

    interface Configuration {
        int getSize();
        PriceSource getSource(int index);
        @Nullable
        PriceProvider getProvider(int index);
    }
}
