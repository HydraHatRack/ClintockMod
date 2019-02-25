package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.TyrosineAction;

public class Tyrosine extends AbstractAminoAcid {
    public static final String LABEL = "Tyrosine";
    private static final int FLUOROPHORE_AMOUNT = 1;

    public Tyrosine() {
        this.baseNumber = FLUOROPHORE_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Tyr: #b" + this.baseNumber + " #yFluorophore -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new TyrosineAction(this.baseNumber);
    }
}
