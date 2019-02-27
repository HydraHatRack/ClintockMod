package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.TryptophanAction;

public class Tryptophan extends AbstractAminoAcid {
    public static final String LABEL = "Tryptophan";
    private static final int STRENGTH_AMOUNT = 1;

    public Tryptophan() {
        this.baseNumber = STRENGTH_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Trp: #b-" + this.baseNumber + " #yStrength -> Enemy";
    }

    @Override
    public AbstractGameAction getAction() {
        return new TryptophanAction(this.baseNumber);
    }
}
