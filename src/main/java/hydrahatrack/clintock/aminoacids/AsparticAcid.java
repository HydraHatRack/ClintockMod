package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.AsparticAcidAction;

public class AsparticAcid extends AbstractAminoAcid {
    public static final String NAME = "Aspartic Acid";
    public static final String CODE = "Asp";
    public static final String DESCRIPTION = "Persist remaining #yBlock into the next turn.";
    public static final String[] DNA_CODONS = {"GAT", "GAC"};
    private static final int BLUR_AMOUNT = 1;

    public AsparticAcid() {
        this.baseNumber = BLUR_AMOUNT;
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
        return new AsparticAcidAction(this.baseNumber);
    }
}
