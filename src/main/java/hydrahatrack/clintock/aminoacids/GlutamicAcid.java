package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.GlutamicAcidAction;

public class GlutamicAcid extends AbstractAminoAcid {
    public static final String NAME = "Glutamic Acid";
    public static final String CODE = "Glu";
    public static final String DESCRIPTION = "#yUpgrade a random card in your hand.";
    public static final String[] DNA_CODONS = {"GAA", "GAG"};
    private static final int UPGRADE_CARD_AMOUNT = 1;

    public GlutamicAcid() {
        this.baseNumber = UPGRADE_CARD_AMOUNT;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String[] getDnaCodons() {
        return DNA_CODONS;
    }

    @Override
    public AbstractGameAction getAction() {
        return new GlutamicAcidAction(this.baseNumber);
    }
}
