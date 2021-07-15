package ccs.coherence.keyperformv2;

import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedMap;

public class SpecKeyProducerMain {
    private static final Logger log = LoggerFactory.getLogger(SpecKeyProducerMain.class);

    // ----- static methods -------------------------------------------------

    public static void main(String[] asArgs) {
        NamedMap<String, LatencyMeasurePing> map = CacheFactory.getCache("welcomes");
        //AsyncNamedMap<String, LatencyMeasurePing> asyncMap = map.async();

        String key = System.getProperty("ccs.perform.key", "defaultkey");
        long loop_ns = 5_000_000_000L; // ns = 5s
        int iter = Integer.valueOf(System.getProperty("ccs.perform.iterate", "20"));

        SecureRandom rand = new SecureRandom();

        try {
            int seq = 0;
            for( int i=0 ; i != iter ; i++ ) {
                int cnt =0;
                long st = System.nanoTime();
                long et = 0;
                while( (et = System.nanoTime()) - st < loop_ns) {
                    map.put(key, new LatencyMeasurePing(seq));
//                    asyncMap.put(key, new LatencyMeasurePing(seq));
                    seq++;
                    cnt++;
                }

                log.info("{}: {} ns. {} times. {} ns/op", key, et-st, cnt, (et-st)/(double)cnt);
            }
        }catch(Throwable th) {
            log.error("occ exception.", th);

        }finally {
            map.close();
        }

    }
}