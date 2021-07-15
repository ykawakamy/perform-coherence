
package ccs.coherence.keyperformv2;

import java.io.IOException;
import java.io.Serializable;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

public class LatencyMeasurePing implements PortableObject, Serializable {
    int seq;
    long sendTick;
    long latency = -1;

    public LatencyMeasurePing(int seq) {
        this.seq = seq;
        // Note : System.nanoTime()はJVMごとで別の値を取るため。
        sendTick = System.currentTimeMillis();
    }

    public LatencyMeasurePing(int seq, long tick) {
        this.seq = seq;
        this.sendTick = tick;
        this.latency =  System.currentTimeMillis() - sendTick;
    }
    public int getSeq() {
        return seq;
    }

    public void resetLatency() {
        this.latency =  System.currentTimeMillis() - sendTick;
    }

    public long getLatency() {
        return latency;
    }

    @Override
    public void readExternal(PofReader in) throws IOException {
        this.seq = in.readInt(0);
        this.sendTick = in.readLong(1);
        this.latency =  System.currentTimeMillis() - sendTick;
    }

    @Override
    public void writeExternal(PofWriter out) throws IOException {
        out.writeInt(0, seq);
        out.writeLong(1, sendTick);

    }



}
