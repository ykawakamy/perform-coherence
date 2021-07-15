package ccs.perform.util;

public class PerformSnapshot {
    int seq;
    int err;
    int perform;
    long latency;

    public int getSeq() {
        return seq;
    }
    public int getErr() {
        return err;
    }
    public int getPerform() {
        return perform;
    }
    public long getLatency() {
        return latency;
    }

    public float getElapsedPerOperation(long elapsedTime ) {
        return (float)elapsedTime/getPerform();

    }
    public float getLatencyPerOperation() {
        return (float)getLatency()/getPerform();
    }

}
