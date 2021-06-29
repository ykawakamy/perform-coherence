package ccs.coherence.single.consumer;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedMap;

public class ConsBusyGetMain {
    // ----- static methods -------------------------------------------------

    public static void main(String[] asArgs) {
        NamedMap<String, String> map = CacheFactory.getCache("welcomes");

        long loop_ns = 5_000_000_000L; // ns = 5s
        int iter = 20;

        for( int i=0 ; i< iter ; i++ ) {
            long st = System.nanoTime();
            long et = 0;
            int cnt =0;
            while( (et = System.nanoTime()) - st < loop_ns) {
                map.get("sameKey");
                cnt++;
            }

            System.out.printf("%d ns. %d times. %f us/op\n", et-st, cnt, (et-st)/(double)cnt);
        }

        map.close();
    }
}