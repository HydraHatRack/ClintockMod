package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.LysineAction;

public class Lysine extends AbstractAminoAcid {
    public static final String LABEL = "Lysine";
    private static final int PLATED_ARMOR_AMOUNT = 1;

    public Lysine() {
        this.baseNumber = PLATED_ARMOR_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Lys: #b" + this.baseNumber + " #yPlated #yArmor -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new LysineAction(this.baseNumber);
    }
}
