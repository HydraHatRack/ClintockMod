package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;

public class GlutamicAcid extends AbstractAminoAcid {
    private static final int UPGRADE_CARD_AMOUNT = 1;

    public GlutamicAcid() {
        this.baseNumber = UPGRADE_CARD_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Glutamic Acid (Upgrade a card in your hand for the rest of combat)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new UpgradeRandomCardAction();
    }
}
