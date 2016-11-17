package com.nns.impl;

import com.nns.FxPrice;
import com.nns.Market;
import com.nns.PriceProvider;
import com.nns.PriceSource;
import com.nns.ReferenceRateCalculator;
import com.nns.ReferenceRateCalculator.Configuration;
import org.testng.annotations.Test;

import static com.nns.impl.builders.ConfigurationBuilder.configurationBuilder;
import static com.nns.impl.builders.FxPriceBuilder.fxPriceBuilder;
import static com.nns.impl.builders.MarketBuilder.marketBuilder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReferenceRateCalculatorImplTest {

    ReferenceRateCalculator referenceRateCalculator;

    final Market m1 = marketBuilder().priceSource(PriceSource.SOURCE1).priceProvider(PriceProvider.PROVIDER1).build();
    final Market m2 = marketBuilder().priceSource(PriceSource.SOURCE2).priceProvider(null).build();
    final Configuration config = configurationBuilder().market(m1).market(m2).build();
    final FxPrice fxPrice = fxPriceBuilder().bid(10).source(PriceSource.SOURCE1).provider(PriceProvider.PROVIDER1).build();
    final FxPrice fxPrice1 = fxPriceBuilder().bid(20).source(PriceSource.SOURCE1).provider(PriceProvider.PROVIDER1).build();
    final FxPrice fxPrice2 = fxPriceBuilder().bid(30).source(PriceSource.SOURCE2).build();
    final FxPrice fxPrice3 = fxPriceBuilder().bid(30).source(PriceSource.SOURCE2).stale(true).build();

    @Test
    public void testOnConfiguration() throws Exception {

        // given
        referenceRateCalculator = new ReferenceRateCalculatorImpl();

        // when
        referenceRateCalculator.onConfiguration(config);

        // then
        assertThat(referenceRateCalculator.getMarketsConfigured().size()).isEqualTo(2);
    }

    @Test
    public void testOnFxPrice() throws Exception {

        // given
        referenceRateCalculator = new ReferenceRateCalculatorImpl();
        referenceRateCalculator.onConfiguration(config);

        // when
        referenceRateCalculator.onFxPrice(fxPrice);
        // then
        assertThat(referenceRateCalculator.getMarketFxPriceList().get(m1).getBid()).isEqualTo(10);

        // when
        referenceRateCalculator.onFxPrice(fxPrice1);
        // then
        assertThat(referenceRateCalculator.getMarketFxPriceList().get(m1).getBid()).isEqualTo(20);

        // when
        referenceRateCalculator.onFxPrice(fxPrice2);
        // then
        assertThat(referenceRateCalculator.getMarketFxPriceList().get(m1).getBid()).isEqualTo(20);
        assertThat(referenceRateCalculator.getMarketFxPriceList().get(m2).getBid()).isEqualTo(30);

    }

    @Test
    public void testOnCalculate() throws Exception {

        // given
        referenceRateCalculator = new ReferenceRateCalculatorImpl();
        referenceRateCalculator.onConfiguration(config);
        referenceRateCalculator.onFxPrice(fxPrice);
        referenceRateCalculator.onFxPrice(fxPrice1);
        referenceRateCalculator.onFxPrice(fxPrice2);

        // when
        final FxPrice medianPrice = referenceRateCalculator.calculate();

        // then
        assertThat(medianPrice.getBid()).isEqualTo(50.0);

    }

    @Test
    public void testOnCalculateWithStalePrice() throws Exception {

        // given
        referenceRateCalculator = new ReferenceRateCalculatorImpl();
        referenceRateCalculator.onConfiguration(config);
        referenceRateCalculator.onFxPrice(fxPrice);
        referenceRateCalculator.onFxPrice(fxPrice1);
        referenceRateCalculator.onFxPrice(fxPrice2);
        referenceRateCalculator.onFxPrice(fxPrice3);

        // when
        final FxPrice medianPrice = referenceRateCalculator.calculate();

        // then
        assertThat(medianPrice.getBid()).isEqualTo(20.0);

    }

    @Test
    public void testOnCalculateWithAllStalePrice() throws Exception {

        // given
        referenceRateCalculator = new ReferenceRateCalculatorImpl();
        referenceRateCalculator.onConfiguration(config);
        referenceRateCalculator.onFxPrice(fxPrice3);

        // when
        final FxPrice medianPrice = referenceRateCalculator.calculate();

        // then
        assertThat(medianPrice.getBid()).isEqualTo(0.0);
    }

}