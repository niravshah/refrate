package com.nns.impl.repository;

import com.nns.Market;
import com.nns.PriceProvider;
import com.nns.PriceSource;
import com.nns.impl.MarketImpl;

import java.util.HashMap;
import java.util.Map;

public class MarketRepository {

    private static Map<PriceSource, Map<PriceProvider, Market>> markets = new HashMap<>();

    public static Market getMarket(PriceSource source, PriceProvider priceProvider) {

        Map<PriceProvider, Market> priceProviderMarketMap = markets.get(source);
        if (priceProviderMarketMap == null) {
            priceProviderMarketMap = new HashMap<>();
            markets.put(source, priceProviderMarketMap);
        }

        Market market = priceProviderMarketMap.get(priceProvider);
        if (market == null) {
            market = new MarketImpl(source, priceProvider);
            priceProviderMarketMap.put(priceProvider, market);
        }

        return market;
    }

}
