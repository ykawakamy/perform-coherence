package ccs.coherence.keyperform;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

public class ModifiyCountListener implements MapListener<String, Integer> {

    AtomicInteger seq = new AtomicInteger();
    AtomicInteger err = new AtomicInteger();

    AtomicInteger perform = new AtomicInteger();

    @Override
    public void entryInserted(MapEvent<String, Integer> evt) {
        int expected = seq.getAndIncrement();
        if( !Objects.equals(expected, evt.getNewValue())  ) {
            err.getAndIncrement();
            seq.set(evt.getNewValue()+1);
        }
        perform.getAndIncrement();
    }

    @Override
    public void entryUpdated(MapEvent<String, Integer> evt) {
        int expected = seq.getAndIncrement();
        if( !Objects.equals(expected, evt.getNewValue())  ) {
            err.getAndIncrement();
            seq.set(evt.getNewValue()+1);
        }
        perform.getAndIncrement();
    }

    @Override
    public void entryDeleted(MapEvent<String, Integer> evt) {

    }

    public int get() {
        return seq.get();
    }

    public int getErr() {
        return err.get();
    }

    public int retrievePerform() {
        int v = perform.getAndSet(0);
        err.set(0);
        return v;
    }

}