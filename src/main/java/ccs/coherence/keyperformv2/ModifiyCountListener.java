package ccs.coherence.keyperformv2;

import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

import ccs.perform.util.SequencialPerformCounter;

public class ModifiyCountListener implements MapListener<String, LatencyMeasurePing> {

    SequencialPerformCounter data = new SequencialPerformCounter();

    @Override
    public void entryInserted(MapEvent<String, LatencyMeasurePing> evt) {
        doInsertOrUpdate(evt);
    }

    @Override
    public void entryUpdated(MapEvent<String, LatencyMeasurePing> evt) {
        doInsertOrUpdate(evt);
    }

    private void doInsertOrUpdate(MapEvent<String, LatencyMeasurePing> evt) {
        LatencyMeasurePing actual = evt.getNewValue();
        data.perform(actual.getSeq());
        actual.resetLatency();
        data.addLatency(actual.getLatency());
    }

    @Override
    public void entryDeleted(MapEvent<String, LatencyMeasurePing> evt) {

    }

    public int get() {
        return data.getSeq();
    }

    public int getErr() {
        return data.getErr();
    }

    public int retrievePerform() {
        return data.retrievePerform();
    }

}