package ccs.coherence.keyperform;

import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

import ccs.perform.util.PerformSnapshot;
import ccs.perform.util.SequencialPerformCounter;

public class ModifiyCountListener implements MapListener<String, Integer> {

    SequencialPerformCounter data = new SequencialPerformCounter();

    @Override
    public void entryInserted(MapEvent<String, Integer> evt) {
        Integer actual = evt.getNewValue();
        data.perform(actual);
    }

    @Override
    public void entryUpdated(MapEvent<String, Integer> evt) {
        Integer actual = evt.getNewValue();
        data.perform(actual);
    }

    @Override
    public void entryDeleted(MapEvent<String, Integer> evt) {

    }

    public PerformSnapshot reset() {
        return data.reset();
    }

}