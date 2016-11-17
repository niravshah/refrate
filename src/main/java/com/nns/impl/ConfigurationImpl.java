package com.nns.impl;

import com.nns.Market;
import com.nns.PriceProvider;
import com.nns.PriceSource;
import com.nns.ReferenceRateCalculator.Configuration;

import java.util.List;

public class ConfigurationImpl implements Configuration {

    private List<Market> markets;

    public ConfigurationImpl(List<Market> markets) {
        this.markets = markets;
    }

    @Override
    public int getSize() {
        return markets.size();
    }

    @Override
    public PriceSource getSource(int index) {
        return markets.get(index).getPriceSource();
    }

    @Override
    public PriceProvider getProvider(int index) {
        return markets.get(index).getPriceProvider();
    }
}
