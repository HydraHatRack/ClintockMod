package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.GlutamineAction;

public class Glutamine extends AbstractAminoAcid {
    public static final String LABEL = "Glutamine";
    private static final int CARD_AMOUNT = 1;

    public Glutamine() {
        this.baseNumber = CARD_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Gln: Add #yCommon card";
    }

    @Override
    public AbstractGameAction getAction() {
        return new GlutamineAction(this.baseNumber);
    }
}
