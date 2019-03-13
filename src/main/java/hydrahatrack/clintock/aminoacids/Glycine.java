package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.GlycineAction;

public class Glycine extends AbstractAminoAcid {
    public static final String NAME = "Glycine";
    public static final String CODE = "Gly";
    public static final String DESCRIPTION = "Apply #b1 #yWeak to a random enemy at the end of your turn.";
    public static final String[] DNA_CODONS = {"GGT", "GGC", "GGA", "GGG"};
    private static final int WEAK_AMOUNT = 1;

    public Glycine() {
        this.baseNumber = WEAK_AMOUNT;
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
        return new GlycineAction(this.baseNumber);
    }
}
