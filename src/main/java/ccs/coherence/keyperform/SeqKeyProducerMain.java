package ccs.coherence.keyperform;

import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedMap;

public class SeqKeyProducerMain {
    private static final Logger log = LoggerFactory.getLogger(SeqKeyProducerMain.class);

    // ----- static methods -------------------------------------------------

    public static void main(String[] asArgs) {
        NamedMap<String, Integer> map = CacheFactory.getCache("welcomes");

        String key = "sequencial";
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
                    map.put(Integer.toString(cnt), seq);
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