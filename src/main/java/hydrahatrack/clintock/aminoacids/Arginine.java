package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.ArginineAction;

public class Arginine extends AbstractAminoAcid {
    public static final String NAME = "Arginine";
    public static final String CODE = "Arg";
    public static final String DESCRIPTION = "Gain #b6 #yBlock at the end of your turn.";
    public static final String[] DNA_CODONS = {"CGT", "CGC", "CGA", "CGG", "AGA", "AGG"};
    private static final int BLOCK_AMOUNT = 6;

    public Arginine() {
        this.baseNumber = BLOCK_AMOUNT;
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
        return new ArginineAction(this.baseNumber);
    }
}
