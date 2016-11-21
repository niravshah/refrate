package com.nns.impl.perftest;

import java.util.List;
import java.util.Random;

public class RandomDataProvider {

    private Random randomGenerator = new Random();

    public static RandomDataProvider randomDataProvider() {
        return new RandomDataProvider();
    }

    public <T> T randomFromList(List<T> list) {
        return list.get(randomGenerator.nextInt(list.size()));
    }

    public boolean randomBoolean() {
        return randomGenerator.nextBoolean();
    }

    public long randomLong() {
        return randomGenerator.nextLong();
    }

}
