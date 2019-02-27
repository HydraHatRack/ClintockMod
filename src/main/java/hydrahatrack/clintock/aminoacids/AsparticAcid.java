package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.AsparticAcidAction;

public class AsparticAcid extends AbstractAminoAcid {
    public static final String LABEL = "Aspartic Acid";
    private static final int BLUR_AMOUNT = 1;

    public AsparticAcid() {
        this.baseNumber = BLUR_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Asp: Persist #yBlock -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new AsparticAcidAction(this.baseNumber);
    }
}
