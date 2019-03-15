package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.ThreonineAction;

public class Threonine extends AbstractAminoAcid {
    public static final String NAME = "Threonine";
    public static final String CODE = "Thr";
    public static final String DESCRIPTION = "Gain #b4 #ySugars at the end of your turn.";
    public static final String[] DNA_CODONS = {"ACT", "ACC", "ACA", "ACG"};
    private static final int SUGAR_AMOUNT = 4;

    public Threonine() {
        this.baseNumber = SUGAR_AMOUNT;
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
        return new ThreonineAction(this.baseNumber);
    }
}
