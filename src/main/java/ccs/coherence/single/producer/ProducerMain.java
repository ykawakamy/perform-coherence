package ccs.coherence.single.producer;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedMap;

public class ProducerMain {
    // ----- static methods -------------------------------------------------

    public static void main(String[] asArgs) {
        NamedMap<String, Integer> map = CacheFactory.getCache("welcomes");

        long loop_ns = 5_000_000_000L; // ns = 5s
        int iter = 100;

        int totalCnt = 0;
        for (int i = 0; i < iter; i++) {
            long st = System.nanoTime();
            long et = 0;
            int cnt = 0;
            while ((et = System.nanoTime()) - st < loop_ns) {
                map.put("sameKey", totalCnt);
                totalCnt++;
                cnt++;
            }

            System.out.printf("%d ns. %d times. %f us/op\n", et - st, cnt, (et - st) / (double) cnt);
        }

        System.out.printf("%d put/total\n", totalCnt);

        map.close();
    }
}