package ccs.coherence.single.producer;

import java.security.SecureRandom;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedMap;

public class ProducerRandomKeyMain {
    // ----- static methods -------------------------------------------------

    public static void main(String[] asArgs) {
        NamedMap<String, Integer> map = CacheFactory.getCache("welcomes");

        String[] datas = {"1","2","3","4","5"};

        long loop_ns = 5_000_000_000L; // ns = 5s
        int iter = 5;

        SecureRandom rand = new SecureRandom();

        for( int i=0 ; i< iter ; i++ ) {
            long st = System.nanoTime();
            long et = 0;
            int cnt =0;
            while( (et = System.nanoTime()) - st < loop_ns) {
                String key = String.valueOf(rand.nextInt());
                map.put(key, cnt);
                cnt++;
            }

           System.out.printf("%d ns. %d times. %f us/op\n", et-st, cnt, (et-st)/(double)cnt);
        }

        map.close();
    }
}