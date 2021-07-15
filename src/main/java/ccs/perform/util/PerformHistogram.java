package ccs.perform.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformHistogram {
    /** ロガー */
    private static final Logger log = LoggerFactory.getLogger(PerformHistogram.class);

    Map<Integer, AtomicInteger> histogram = new ConcurrentHashMap<>();

    public void increament(long n) {
        AtomicInteger bucket = histogram.computeIfAbsent(convertKey(n), (k) -> new AtomicInteger());
        bucket.getAndIncrement();
    }

    private Integer convertKey(long n) {
        if (n < 1000L) {
            return (int) (n / 100L) * 100 + 100;
        }
        if (n < 10000L) {
            return (int) (n / 1000L) * 1000 + 1000;
        }
        return Integer.MAX_VALUE;
    }

    public void printLog() {
        List<Integer> keys = new ArrayList<>(histogram.keySet());
        keys.sort((l, r) -> l-r);
        log.info("----");
        for (var key : keys) {
            if (key.equals(Integer.MAX_VALUE))
                log.info("～ : {}", key, histogram.get(key));
            else
                log.info("～{} : {}", key, histogram.get(key));
        }
    }

    public void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            printLog();
        }));
    }

}
