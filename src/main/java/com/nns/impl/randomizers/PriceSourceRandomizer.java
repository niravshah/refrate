package com.nns.impl.randomizers;

import com.nns.PriceSource;
import io.github.benas.randombeans.api.Randomizer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PriceSourceRandomizer implements Randomizer<PriceSource> {

    private List<PriceSource> priceSources = Arrays.asList(PriceSource.SOURCE1, PriceSource.SOURCE2, PriceSource.SOURCE3);

    @Override
    public PriceSource getRandomValue() {
        return priceSources.get(new Random().nextInt(2));
    }
}
