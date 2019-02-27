package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.ValineAction;

public class Valine extends AbstractAminoAcid {
    public static final String LABEL = "Valine";
    private static final int PHOSPHATE_AMOUNT = 2;

    public Valine() {
        this.baseNumber = PHOSPHATE_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Val: #b" + this.baseNumber + " #yPhosphates -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ValineAction(this.baseNumber);
    }
}
