package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.IsoleucineAction;

public class Isoleucine extends AbstractAminoAcid {
    public static final String LABEL = "Isoleucine";
    private static final int THORNS_AMOUNT = 3;

    public Isoleucine() {
        this.baseNumber = THORNS_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Ile: #b" + this.baseNumber + " #yThorns -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new IsoleucineAction(this.baseNumber);
    }
}
