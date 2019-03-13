package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.AsparagineAction;

public class Asparagine extends AbstractAminoAcid {
    public static final String NAME = "Asparagine";
    public static final String CODE = "Asn";
    public static final String DESCRIPTION = "Gain #b1 #yDexterity at the end of your turn.";
    public static final String[] DNA_CODONS = {"AAT", "AAC"};
    private static final int DEXTERITY_AMOUNT = 1;

    public Asparagine() {
        this.baseNumber = DEXTERITY_AMOUNT;
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
        return new AsparagineAction(this.baseNumber);
    }
}
