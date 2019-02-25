package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.SerineAction;

public class Serine extends AbstractAminoAcid {
    public static final String LABEL = "Serine";
    private static final int POISON_AMOUNT = 3;

    public Serine() {
        this.baseNumber = POISON_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Ser: #b" + this.baseNumber + " #yPoison -> Enemy";
    }

    @Override
    public AbstractGameAction getAction() {
        return new SerineAction(this.baseNumber);
    }
}
