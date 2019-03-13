package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.TryptophanAction;

public class Tryptophan extends AbstractAminoAcid {
    public static final String NAME = "Tryptophan";
    public static final String CODE = "Trp";
    public static final String DESCRIPTION = "Remove #b1 #yStrength from a random enemy at the end of your turn.";
    public static final String[] DNA_CODONS = {"TGG"};
    private static final int STRENGTH_AMOUNT = 1;

    public Tryptophan() {
        this.baseNumber = STRENGTH_AMOUNT;
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
        return new TryptophanAction(this.baseNumber);
    }
}
