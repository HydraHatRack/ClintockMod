package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class StopCodon extends AbstractAminoAcid {
    public static final String LABEL = "Stop Codon";

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Stop: Does nothing";
    }

    @Override
    public AbstractGameAction getAction() {
        return null;
    }
}
