package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.MethionineAction;

public class Methionine extends AbstractAminoAcid {
    public static final String LABEL = "Methionine";
    private static final int HEALTH_AMOUNT = 1;

    public Methionine() {
        this.baseNumber = HEALTH_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Met: Heal #b" + this.baseNumber + " HP -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new MethionineAction(this.baseNumber);
    }
}
