package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.PhenylalanineAction;

public class Phenylalanine extends AbstractAminoAcid {
    public static final String LABEL = "Phenylalanine";
    private static final int CARD_AMOUNT = 1;

    public Phenylalanine() {
        this.baseNumber = CARD_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Phe: Add #yUncommon card";
    }

    @Override
    public AbstractGameAction getAction() {
        return new PhenylalanineAction(this.baseNumber);
    }
}
