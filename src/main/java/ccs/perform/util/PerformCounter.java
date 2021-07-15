package ccs.perform.util;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PerformCounter {
    private AtomicInteger seq = new AtomicInteger();
    private AtomicInteger err = new AtomicInteger();
    private AtomicInteger perform = new AtomicInteger();
    private AtomicLong latency = new AtomicLong();

    public PerformCounter() {
    }

    public int getSeq() {
        return seq.get();
    }

    public int getErr() {
        return err.get();
    }

    public int getPerform() {
        return perform.get();
    }

    public int retrievePerform() {
        int v = perform.getAndSet(0);
        err.set(0);
        return v;
    }

    public void perform(Integer actual) {
        int expected = seq.getAndIncrement();
        if( !Objects.equals(expected, actual)  ) {
            err.getAndIncrement();
            seq.set(actual+1);
        }
        perform.getAndIncrement();
    }

    public void addLatency(long n) {
        latency.addAndGet(n);
    }

    public PerformSnapshot reset() {
        PerformSnapshot snap = new PerformSnapshot();
        snap.err = err.getAndSet(0);
        snap.perform = perform.getAndSet(0);
        snap.latency = latency.getAndSet(0);
        return snap;

    }
}