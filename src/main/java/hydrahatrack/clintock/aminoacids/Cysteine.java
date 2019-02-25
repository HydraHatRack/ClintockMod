package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.CysteineAction;

public class Cysteine extends AbstractAminoAcid {
    public static final String LABEL = "Cysteine";
    private static final int STRENGTH_AMOUNT = 1;

    public Cysteine() {
        this.baseNumber = STRENGTH_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Cys: #b" + this.baseNumber + " #yStrength -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new CysteineAction(this.baseNumber);
    }
}
