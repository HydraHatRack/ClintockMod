package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.AlanineAction;

public class Alanine extends AbstractAminoAcid {
    public static final String NAME = "Alanine";
    public static final String CODE = "Ala";
    public static final String DESCRIPTION = "Gain #b1 #yEnergy at the start of your next turn.";
    public static final String[] DNA_CODONS = {"GCT", "GCC", "GCA", "GCG"};
    private static final int ENERGY_AMOUNT = 1;

    public Alanine() {
        this.baseNumber = ENERGY_AMOUNT;
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
        return new AlanineAction(this.baseNumber);
    }
}
