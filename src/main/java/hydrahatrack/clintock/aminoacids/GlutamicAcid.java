package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.GlutamicAcidAction;

public class GlutamicAcid extends AbstractAminoAcid {
    public static final String LABEL = "Glutamic Acid";
    private static final int UPGRADE_CARD_AMOUNT = 1;

    public GlutamicAcid() {
        this.baseNumber = UPGRADE_CARD_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Gln: #yUpgrade card in hand";
    }

    @Override
    public AbstractGameAction getAction() {
        return new GlutamicAcidAction(this.baseNumber);
    }
}
