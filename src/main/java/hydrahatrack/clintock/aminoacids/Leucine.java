package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.LeucineAction;

public class Leucine extends AbstractAminoAcid {
    public static final String NAME = "Leucine";
    public static final String CODE = "Leu";
    public static final String DESCRIPTION = "Deal #b6 damage to a random enemy at the end of your turn.";
    public static final String[] DNA_CODONS = {"CTT", "CTC", "CTA", "CTG", "TTA", "TTG"};
    private static final int DAMAGE_AMOUNT = 6;

    public Leucine() {
        this.baseNumber = DAMAGE_AMOUNT;
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
        return new LeucineAction(this.baseNumber);
    }
}
