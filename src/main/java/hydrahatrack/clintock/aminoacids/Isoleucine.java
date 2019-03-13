package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.IsoleucineAction;

public class Isoleucine extends AbstractAminoAcid {
    public static final String NAME = "Isoleucine";
    public static final String CODE = "Ile";
    public static final String DESCRIPTION = "Gain #b2 #yThorns at the end of your turn.";
    public static final String[] DNA_CODONS = {"ATT", "ATC", "ATA"};
    private static final int THORNS_AMOUNT = 2;

    public Isoleucine() {
        this.baseNumber = THORNS_AMOUNT;
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
        return new IsoleucineAction(this.baseNumber);
    }
}
