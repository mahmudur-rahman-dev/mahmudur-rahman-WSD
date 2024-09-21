package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CertificateUpdateGeneratorTest {

    private final CertificateUpdateGenerator generator = new CertificateUpdateGenerator(1, 1);

    @Test
    void testGenerateQuote() throws Exception {
        Callable<String> quoteCallable = generator.generateQuotes().findFirst().orElseThrow();
        String quote = quoteCallable.call();

        assertNotNull(quote);
        String[] parts = quote.split(",");
        assertEquals(6, parts.length);

        assertTrue(Long.parseLong(parts[0]) > 0);

        assertTrue(parts[1].matches("[A-Z]{2}[A-Z0-9]{10}"));

        double bidPrice = Double.parseDouble(parts[2]);
        assertTrue(bidPrice >= 100.00 && bidPrice <= 200.00);


        int bidSize = Integer.parseInt(parts[3]);
        assertTrue(bidSize >= 1000 && bidSize <= 5000);


        double askPrice = Double.parseDouble(parts[4]);
        assertTrue(askPrice >= 100.00 && askPrice <= 200.00);


        int askSize = Integer.parseInt(parts[5]);
        assertTrue(askSize >= 1000 && askSize <= 10000);
    }
}