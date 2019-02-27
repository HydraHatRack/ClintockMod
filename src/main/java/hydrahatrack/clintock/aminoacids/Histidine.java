package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.HistidineAction;

public class Histidine extends AbstractAminoAcid {
    public static final String LABEL = "Histidine";

    public Histidine() {}

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL His: Remove #yBlock -> Enemy";
    }

    @Override
    public AbstractGameAction getAction() {
        return new HistidineAction();
    }
}
