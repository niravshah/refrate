package com.nns.impl.randomizers;

import com.nns.PriceProvider;
import io.github.benas.randombeans.api.Randomizer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PriceProviderRandomizer implements Randomizer<PriceProvider> {

    private List<PriceProvider> priceProviders = Arrays.asList(PriceProvider.PROVIDER1, PriceProvider.PROVIDER2, null);

    @Override
    public PriceProvider getRandomValue() {
        return priceProviders.get(new Random().nextInt(2));
    }

}
