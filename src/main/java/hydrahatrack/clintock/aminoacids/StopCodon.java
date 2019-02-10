package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class StopCodon extends AbstractAminoAcid {
    @Override
    public String getLabel() {
        return " NL STOP (Does nothing)";
    }

    @Override
    public AbstractGameAction getAction() {
        return null;
    }
}
