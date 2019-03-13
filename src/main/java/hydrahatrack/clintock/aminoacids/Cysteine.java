package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.CysteineAction;

public class Cysteine extends AbstractAminoAcid {
    public static final String NAME = "Cysteine";
    public static final String CODE = "Cys";
    public static final String DESCRIPTION = "Gain #b1 #yStrength at the end of your turn.";
    public static final String[] DNA_CODONS = {"TGT", "TGC"};
    private static final int STRENGTH_AMOUNT = 1;

    public Cysteine() {
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
        return new CysteineAction(this.baseNumber);
    }
}
