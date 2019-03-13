package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.LysineAction;

public class Lysine extends AbstractAminoAcid {
    public static final String NAME = "Lysine";
    public static final String CODE = "Lys";
    public static final String DESCRIPTION = "Gain #b1 #yPlated #yArmor at the end of your turn.";
    public static final String[] DNA_CODONS = {"AAA", "AAG"};
    private static final int PLATED_ARMOR_AMOUNT = 1;

    public Lysine() {
        this.baseNumber = PLATED_ARMOR_AMOUNT;
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
        return new LysineAction(this.baseNumber);
    }
}
