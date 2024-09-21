package com.solvians.showcase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public App(String threads, String quotes) {

    }

    public static void main(String[] args) {
        if(args.length < 2) {
            throw new RuntimeException("Expect at least number of threads and number of quotes. But got: " + Arrays.toString(args));
        }

        int threads = Integer.parseInt(args[0]);
        int quotes = Integer.parseInt(args[1]);

        var certificateUpdateGenerator = new CertificateUpdateGenerator(quotes);
        List<Callable<String>> taskList = certificateUpdateGenerator.generateQuotes().collect(Collectors.toList());
        var executor = Executors.newFixedThreadPool(threads);

        try{
            List<String> results = executor.invokeAll(taskList)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception ex) {
                            throw  new RuntimeException(ex);
                        }
                    })
                    .collect(Collectors.toList());
            results.forEach(System.out::println);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("interrupted Thread: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
