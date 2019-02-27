package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.GlycineAction;

public class Glycine extends AbstractAminoAcid {
    public static final String LABEL = "Glycine";
    private static final int WEAK_AMOUNT = 1;

    public Glycine() {
        this.baseNumber = WEAK_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Gly: #b" + this.baseNumber + " #yWeak -> Enemy";
    }

    @Override
    public AbstractGameAction getAction() {
        return new GlycineAction(this.baseNumber);
    }
}
