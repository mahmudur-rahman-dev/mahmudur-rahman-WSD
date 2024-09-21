package com.solvians.showcase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int threads;
    private final int quotes;
    private final ISINGenerator isinGenerator;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
        this.isinGenerator = new ISINGenerator();
    }

//    public Stream<CertificateUpdate> generateQuotes() {
//        return Stream.generate(this::generateSingleQuote)
//                .parallel()
//                .limit((long) threads * quotes);
//    }

    public Stream<Callable<String>> generateQuotes() {
        return Stream.generate(this::createCallable)
                .limit((long) threads * quotes);
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
