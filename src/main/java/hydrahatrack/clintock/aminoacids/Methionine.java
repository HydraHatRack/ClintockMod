package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.MethionineAction;

public class Methionine extends AbstractAminoAcid {
    public static final String NAME = "Methionine";
    public static final String CODE = "Met";
    public static final String DESCRIPTION = "Heal #b1 #yHP at the end of your turn.";
    public static final String[] DNA_CODONS = {"ATG"};
    private static final int HEALTH_AMOUNT = 1;

    public Methionine() {
        this.baseNumber = HEALTH_AMOUNT;
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
        return new MethionineAction(this.baseNumber);
    }
}
