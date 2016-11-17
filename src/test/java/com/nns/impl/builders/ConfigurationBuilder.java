package com.nns.impl.builders;

import com.nns.Market;
import com.nns.ReferenceRateCalculator.Configuration;
import com.nns.impl.ConfigurationImpl;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationBuilder {

    private List<Market> markets = new ArrayList<>();

    public static ConfigurationBuilder configurationBuilder() {
        return new ConfigurationBuilder();
    }

    public ConfigurationBuilder market(Market market) {
        this.markets.add(market);
        return this;
    }

    public Configuration build() {
        return new ConfigurationImpl(markets);
    }
}
