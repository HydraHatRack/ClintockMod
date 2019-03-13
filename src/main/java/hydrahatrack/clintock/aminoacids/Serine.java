package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.SerineAction;

public class Serine extends AbstractAminoAcid {
    public static final String NAME = "Serine";
    public static final String CODE = "Ser";
    public static final String DESCRIPTION = "Apply #b3 #yPoison to a random enemy at the end of your turn.";
    public static final String[] DNA_CODONS = {"TCT", "TCC", "TCA", "TCG", "AGT", "AGC"};
    private static final int POISON_AMOUNT = 3;

    public Serine() {
        this.baseNumber = POISON_AMOUNT;
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
        return new SerineAction(this.baseNumber);
    }
}
