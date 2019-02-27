package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.AlanineAction;

public class Alanine extends AbstractAminoAcid {
    public static final String LABEL = "Alanine";
    private static final int ENERGY_AMOUNT = 1;

    public Alanine() {
        this.baseNumber = ENERGY_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Ala: [R] -> Self (next turn)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new AlanineAction(this.baseNumber);
    }
}
