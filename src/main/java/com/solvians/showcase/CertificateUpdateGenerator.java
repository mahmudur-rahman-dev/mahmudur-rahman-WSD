package com.solvians.showcase;

import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int quotes;
    private final ISINGenerator isinGenerator;

    public CertificateUpdateGenerator(int quotes) {
        this.quotes = quotes;
        this.isinGenerator = new ISINGenerator();
    }

    public Stream<CertificateUpdateTask> generateQuotes() {
        return Stream.generate(() -> new CertificateUpdateTask(isinGenerator))
                .limit(quotes);
    }
}
