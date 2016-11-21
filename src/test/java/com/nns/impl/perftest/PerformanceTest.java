package com.nns.impl.perftest;

import com.google.common.base.Stopwatch;
import com.nns.FxPrice;
import com.nns.Market;
import com.nns.PriceProvider;
import com.nns.PriceSource;
import com.nns.ReferenceRateCalculator;
import com.nns.impl.ConfigurationImpl;
import com.nns.impl.FxPriceImpl;
import com.nns.impl.MarketImpl;
import com.nns.impl.ReferenceRateCalculatorImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nns.ReferenceRateCalculator.Configuration;
import static com.nns.impl.perftest.RandomDataProvider.randomDataProvider;

public class PerformanceTest {

    private static final int TEST_POPULATION_SIZE = 10000;
    private final Stopwatch timer = Stopwatch.createUnstarted();

    ReferenceRateCalculator calculator;
    RandomDataProvider dataProvider;

    private List<Market> testMarkets = Arrays.asList(
            new MarketImpl(PriceSource.SOURCE1, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE2, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE3, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE4, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE5, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE6, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE7, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE8, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE9, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE10, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE11, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE12, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE13, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE14, PriceProvider.PROVIDER1),
            new MarketImpl(PriceSource.SOURCE15, PriceProvider.PROVIDER1)
    );

    @BeforeTest
    public void setup() {
        calculator = new ReferenceRateCalculatorImpl();
        dataProvider = randomDataProvider();
    }

    @Test
    public void perftest() {

        final Configuration configuration = new ConfigurationImpl(testMarkets);
        calculator.onConfiguration(configuration);

        final List<FxPrice> fxPrices = fxPriceList();
        fxPrices
                .stream()
                .forEach(fxPrice -> {
                    calculator.onFxPrice(fxPrice);
                    timer.start();
                    calculator.calculate();
                    System.out.println(timer.stop());
                    timer.reset();
                });
    }

    private List<FxPrice> fxPriceList() {

        List<FxPrice> fxPrices = new ArrayList<>(TEST_POPULATION_SIZE);
        for (int i = 0; i < TEST_POPULATION_SIZE; i++) {
            fxPrices.add(new FxPriceImpl(dataProvider.randomLong(), dataProvider.randomLong(), dataProvider.randomFromList(testMarkets), dataProvider.randomBoolean()));
        }
        return fxPrices;
    }

}
