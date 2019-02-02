package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class StopCodon extends AbstractAminoAcid {
    @Override
    public String getLabel() {
        return "STOP";
    }

    @Override
    public AbstractGameAction getAction() {
        return null;
    }
}
