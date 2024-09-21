package com.solvians.showcase;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int quotes;
    private final ISINGenerator isinGenerator;

    public CertificateUpdateGenerator(int quotes) {
        this.quotes = quotes;
        this.isinGenerator = new ISINGenerator();
    }

    public Stream<Callable<String>> generateQuotes() {
        return Stream.generate(this::createCallable)
                .limit(quotes);
    }

    public Callable<String> createCallable() {
        return () -> generateSingleQuote().toString();
    }


    private CertificateUpdate generateSingleQuote() {
        var threadLocalRandom = ThreadLocalRandom.current();
        return new CertificateUpdate(
                System.currentTimeMillis(),
                isinGenerator.generateISIN(),
                threadLocalRandom.nextDouble(100.00, 200.01),
                threadLocalRandom.nextInt(1000, 5001),
                threadLocalRandom.nextDouble(100.00, 200.01),
                threadLocalRandom.nextInt(1000, 10001)
        );
    }
}
