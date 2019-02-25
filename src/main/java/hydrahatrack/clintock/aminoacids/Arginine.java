package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.ArginineAction;

public class Arginine extends AbstractAminoAcid {
    public static final String LABEL = "Arginine";
    private static final int BLOCK_AMOUNT = 6;

    public Arginine() {
        this.baseNumber = BLOCK_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Arg: #b" + this.baseNumber + " #yBlock -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ArginineAction(this.baseNumber);
    }
}
