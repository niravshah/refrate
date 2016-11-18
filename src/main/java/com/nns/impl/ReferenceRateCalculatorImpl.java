package com.nns.impl;

import com.nns.FxPrice;
import com.nns.Market;
import com.nns.ReferenceRateCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.nns.impl.repository.MarketRepository.getMarket;

public class ReferenceRateCalculatorImpl implements ReferenceRateCalculator {

    private List<Market> marketsConfigured;
    private Map<Market, FxPrice> marketFxPriceList;

    @Override
    public void onConfiguration(Configuration configuration) {
        int size = configuration.getSize();
        marketsConfigured = new ArrayList<>(size);
        marketFxPriceList = new HashMap<>();
        for (int i = 0; i < size; i++) {
            Market market = getMarket(configuration.getSource(i), configuration.getProvider(i));
            marketsConfigured.add(market);
            marketFxPriceList.put(market, null);
        }
    }

    @Override
    public void onFxPrice(FxPrice fxPrice) {
        Market market = getMarket(fxPrice.getSource(), fxPrice.getProvider());
        if (marketsConfigured.contains(market)) {
            marketFxPriceList.put(market, fxPrice);
        }
    }

    @Override
    public FxPrice calculate() {

        double median = 0.0;
        boolean stale = true;

        List<FxPrice> fxPrices = marketFxPriceList
                .values()
                .stream()
                .filter(Objects::nonNull)
                .filter(FxPrice::isNotStale)
                .sorted()
                .collect(Collectors.toList());

        if (fxPrices.size() > 0) {
            stale = false;
            median = calculateMedian(fxPrices);
        }

        return new FxPriceImpl(median, median, null, null, stale);
    }

    private double calculateMedian(List<FxPrice> fxPrices) {
        int size = fxPrices.size();
        FxPrice fxPrice = fxPrices.get(size / 2);
        if (size % 2 == 0) {
            FxPrice fxPrice1 = fxPrices.get(size / 2 - 1);
            return fxPrice.getBid() + fxPrice.getOffer() + fxPrice1.getBid() + fxPrice1.getOffer() / 4;
        } else {
            return fxPrice.getBid() + fxPrice.getOffer() / 2;
        }
    }

    @Override
    public List<Market> getMarketsConfigured() {
        return marketsConfigured;
    }

    @Override
    public Map<Market, FxPrice> getMarketFxPriceList() {
        return marketFxPriceList;
    }
}
