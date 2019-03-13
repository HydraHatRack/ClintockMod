package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.ProlineAction;

public class Proline extends AbstractAminoAcid {
    public static final String NAME = "Proline";
    public static final String CODE = "Pro";
    public static final String DESCRIPTION = "Apply #b2 #yVulnerable to a random enemy at the end of your turn.";
    public static final String[] DNA_CODONS = {"CCT", "CCC", "CCA", "CCG"};
    private static final int VULNERABLE_AMOUNT = 2;

    public Proline() {
        this.baseNumber = VULNERABLE_AMOUNT;
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
        return new ProlineAction(this.baseNumber);
    }
}
