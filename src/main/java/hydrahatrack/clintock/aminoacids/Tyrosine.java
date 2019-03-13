package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.TyrosineAction;

public class Tyrosine extends AbstractAminoAcid {
    public static final String NAME = "Tyrosine";
    public static final String CODE = "Tyr";
    public static final String DESCRIPTION = "Gain #b1 #yFluorophore at the end of your turn.";
    public static final String[] DNA_CODONS = {"TAT", "TAC"};
    private static final int FLUOROPHORE_AMOUNT = 1;

    public Tyrosine() {
        this.baseNumber = FLUOROPHORE_AMOUNT;
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
        return new TyrosineAction(this.baseNumber);
    }
}
