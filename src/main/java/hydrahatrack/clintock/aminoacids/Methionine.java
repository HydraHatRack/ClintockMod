package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class Methionine extends AbstractAminoAcid {
    @Override
    public String getLabel() {
        return "Met";
    }

    @Override
    public AbstractGameAction getAction() {
        return null;
    }
}
