package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.GlutamineAction;

public class Glutamine extends AbstractAminoAcid {
    public static final String NAME = "Glutamine";
    public static final String CODE = "Gln";
    public static final String DESCRIPTION = "Add a random common card to your draw pile.";
    public static final String[] DNA_CODONS = {"CAA", "CAG"};
    private static final int CARD_AMOUNT = 1;

    public Glutamine() {
        this.baseNumber = CARD_AMOUNT;
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
        return new GlutamineAction(this.baseNumber);
    }
}
