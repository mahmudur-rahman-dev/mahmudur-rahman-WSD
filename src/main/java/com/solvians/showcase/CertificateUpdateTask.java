package com.solvians.showcase;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class CertificateUpdateTask implements Callable<String> {
    private final ISINGenerator isinGenerator;

    public CertificateUpdateTask(ISINGenerator isinGenerator) {
        this.isinGenerator = isinGenerator;
    }

    @Override
    public String call() {
        var threadLocalRandom = ThreadLocalRandom.current();
        CertificateUpdate certificateUpdate = new CertificateUpdate(
                System.currentTimeMillis(),
                isinGenerator.generateISIN(),
                threadLocalRandom.nextDouble(100.00, 200.01),
                threadLocalRandom.nextInt(1000, 5001),
                threadLocalRandom.nextDouble(100.00, 200.01),
                threadLocalRandom.nextInt(1000, 10001)
        );
        return certificateUpdate.toString();
    }
}