package ccs.coherence.single.consumer;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedMap;
import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

public class ConsListenerMain {
    // ----- static methods -------------------------------------------------

    public static class ModifiyCountListener implements MapListener<String, Integer> {

        AtomicInteger counter = new AtomicInteger();
        AtomicInteger err = new AtomicInteger();


        @Override
        public void entryInserted(MapEvent<String, Integer> evt) {
            int expected = counter.getAndIncrement();
            if( !Objects.equals(expected, evt.getNewValue())  ) {
                err.getAndIncrement();
            }
        }

        @Override
        public void entryUpdated(MapEvent<String, Integer> evt) {
            int expected = counter.getAndIncrement();
            if( !Objects.equals(expected, evt.getNewValue())  ) {
                err.getAndIncrement();
            }
        }

        @Override
        public void entryDeleted(MapEvent<String, Integer> evt) {

        }

        public int get() {
            return counter.get();
        }

        public int getErr() {
            return err.get();
        }

    }

    public static void main(String[] asArgs) throws InterruptedException {
        NamedMap<String, Integer> map = CacheFactory.getCache("welcomes");
        ModifiyCountListener listener = new ModifiyCountListener();
        map.addMapListener(listener);

        long loop_ns = 5_000_000_000L; // ns = 5s
        int iter = 20;

        for( int i=0 ; i< iter ; i++ ) {
            long st = System.nanoTime();
            long et = 0;
            int cnt =0;
            TimeUnit.NANOSECONDS.sleep(loop_ns);
            et = System.nanoTime();

            System.out.printf("%d op, %d errors \n", listener.get(), listener.getErr() );
        }

        map.close();
    }
}