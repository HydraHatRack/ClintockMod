package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.ValineAction;

public class Valine extends AbstractAminoAcid {
    public static final String NAME = "Valine";
    public static final String CODE = "Val";
    public static final String DESCRIPTION = "Gain #b2 #yPhosphates at the end of your turn.";
    public static final String[] DNA_CODONS = {"GTT", "GTC", "GTA", "GTG"};
    private static final int PHOSPHATE_AMOUNT = 2;

    public Valine() {
        this.baseNumber = PHOSPHATE_AMOUNT;
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
        return new ValineAction(this.baseNumber);
    }
}
