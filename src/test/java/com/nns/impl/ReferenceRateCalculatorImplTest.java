package com.nns.impl;

import com.nns.FxPrice;
import com.nns.Market;
import com.nns.PriceProvider;
import com.nns.PriceSource;
import com.nns.ReferenceRateCalculator;
import com.nns.ReferenceRateCalculator.Configuration;
import org.testng.annotations.Test;

import static com.nns.impl.ConfigurationBuilder.configurationBuilder;
import static com.nns.impl.builders.FxPriceBuilder.fxPriceBuilder;
import static com.nns.impl.builders.MarketBuilder.marketBuilder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReferenceRateCalculatorImplTest {

    @Test
    public void testOnConfiguration() throws Exception {

        // given
        ReferenceRateCalculator referenceRateCalculator = new ReferenceRateCalculatorImpl();
        Market m1 = marketBuilder().priceSource(PriceSource.SOURCE1).priceProvider(PriceProvider.PROVIDER1).build();
        Market m2 = marketBuilder().priceSource(PriceSource.SOURCE2).priceProvider(null).build();
        Configuration config = configurationBuilder().market(m1).market(m2).build();

        // when
        referenceRateCalculator.onConfiguration(config);

        // then
        assertThat(referenceRateCalculator.getMarketsConfigured().size()).isEqualTo(2);
    }

    @Test
    public void testOnFxPrice() throws Exception {

        // given
        ReferenceRateCalculator referenceRateCalculator = new ReferenceRateCalculatorImpl();
        Market m1 = marketBuilder().priceSource(PriceSource.SOURCE1).priceProvider(PriceProvider.PROVIDER1).build();
        Market m2 = marketBuilder().priceSource(PriceSource.SOURCE2).priceProvider(null).build();
        Configuration config = configurationBuilder().market(m1).market(m2).build();
        referenceRateCalculator.onConfiguration(config);


        final FxPrice fxPrice = fxPriceBuilder().bid(10).source(PriceSource.SOURCE1).provider(PriceProvider.PROVIDER1).build();
        final FxPrice fxPrice1 = fxPriceBuilder().bid(20).source(PriceSource.SOURCE1).provider(PriceProvider.PROVIDER1).build();
        final FxPrice fxPrice2 = fxPriceBuilder().bid(30).source(PriceSource.SOURCE2).build();



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

}