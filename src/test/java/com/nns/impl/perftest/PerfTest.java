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
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static com.nns.ReferenceRateCalculator.Configuration;

public class PerfTest {

    private final static Logger LOGGER = Logger.getLogger(PerfTest.class.getName());
    private final ReferenceRateCalculator calculator = new ReferenceRateCalculatorImpl();
    private static final int TEST_POPULATION_SIZE = 1000;
    private Random randomGenerator = new Random();
    private final Stopwatch timer = Stopwatch.createUnstarted();

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

    @Test
    public void perftest() {

        final Configuration configuration = new ConfigurationImpl(testMarkets);
        calculator.onConfiguration(configuration);

        final List<FxPrice> fxPrices = fxPriceList();

        fxPrices
                .stream()
                .forEach(fxPrice -> {
                    timer.start();
                    calculator.onFxPrice(fxPrice);
                    calculator.calculate();
                    LOGGER.info("Median Calculated In: " + timer.stop());
                    timer.reset();
                });
    }

    private List<FxPrice> fxPriceList() {

        List<FxPrice> fxPrices = new ArrayList<>(TEST_POPULATION_SIZE);
        for (int i = 0; i < TEST_POPULATION_SIZE; i++) {
            fxPrices.add(new FxPriceImpl(randomLong(), randomLong(), randomMarket(), randomBoolean()));
        }
        return fxPrices;
    }

    private Market randomMarket() {
        return testMarkets.get(randomGenerator.nextInt(testMarkets.size()));
    }

    private boolean randomBoolean() {
        return randomGenerator.nextBoolean();
    }

    private long randomLong() {
        return randomGenerator.nextLong();
    }
}
