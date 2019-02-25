package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.LeucineAction;

public class Leucine extends AbstractAminoAcid {
    public static final String LABEL = "Leucine";
    private static final int DAMAGE_AMOUNT = 6;

    public Leucine() {
        this.baseNumber = DAMAGE_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Leu: #b" + this.baseNumber + " damage -> Enemy";
    }

    @Override
    public AbstractGameAction getAction() {
        return new LeucineAction(this.baseNumber);
    }
}
