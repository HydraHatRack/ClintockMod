package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.AsparagineAction;

public class Asparagine extends AbstractAminoAcid {
    public static final String LABEL = "Asparagine";
    private static final int DEXTERITY_AMOUNT = 1;

    public Asparagine() {
        this.baseNumber = DEXTERITY_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Asn: #b" + this.baseNumber + " #yDexterity -> Self";
    }

    @Override
    public AbstractGameAction getAction() {
        return new AsparagineAction(this.baseNumber);
    }
}
