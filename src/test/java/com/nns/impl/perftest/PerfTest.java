package com.nns.impl.perftest;

import com.nns.impl.FxPriceImpl;
import com.nns.impl.MarketImpl;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static io.github.benas.randombeans.api.EnhancedRandom.randomStreamOf;

public class PerfTest {

    @Test
    public void perftest() {

        randomStreamOf(20, MarketImpl.class).forEach(market -> System.out.println(market));

        final Stream<FxPriceImpl> fxPriceStream = randomStreamOf(10, FxPriceImpl.class);

        fxPriceStream.forEach(fxPrice -> System.out.println(fxPrice));
    }

}
