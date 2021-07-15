package ccs.coherence.keyperform;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedMap;

public class AllKeyListenConsumerMain {
    private static final Logger log = LoggerFactory.getLogger(AllKeyListenConsumerMain.class);
    // ----- static methods -------------------------------------------------

    public static void main(String[] asArgs) throws InterruptedException {
        NamedMap<String, Integer> map = CacheFactory.getCache("welcomes");
        ModifiyCountListener listener = new ModifiyCountListener();

        String key = "all key";
        long loop_ns = 5_000_000_000L; // ns = 5s
        int iter = Integer.valueOf(System.getProperty("ccs.perform.iterate", "20"));

        map.addMapListener(listener);

        try {
            for( int i=0 ; i != iter ; i++ ) {
                long st = System.nanoTime();
                long et = 0;
                TimeUnit.NANOSECONDS.sleep(loop_ns);
                et = System.nanoTime();

                int count = listener.retrievePerform();
                log.info("{}: {} op, {} errors, {} ns/op", key, count, listener.getErr(), (double)(et-st)/count );
            }
        }catch(Throwable th) {
            log.error("occ exception.", th);
        }finally {
            map.close();
        }
    }
}