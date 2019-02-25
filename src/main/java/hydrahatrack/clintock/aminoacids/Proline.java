package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.ProlineAction;

public class Proline extends AbstractAminoAcid {
    public static final String LABEL = "Proline";
    private static final int VULNERABLE_AMOUNT = 2;

    public Proline() {
        this.baseNumber = VULNERABLE_AMOUNT;
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

    @Override
    public String getDescription() {
        return " NL Pro: #b" + this.baseNumber + " #yVulnerable -> Enemy";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ProlineAction(this.baseNumber);
    }
}
