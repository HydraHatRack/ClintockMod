package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.ThreonineAction;

public class Threonine extends AbstractAminoAcid {
    public static final String LABEL = "Threonine";
    private static final int SUGAR_AMOUNT = 2;

    public Threonine() {
        this.baseNumber = SUGAR_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Thr: #b" + this.baseNumber + " #ySugars -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ThreonineAction(this.baseNumber);
    }
}
