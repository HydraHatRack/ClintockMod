package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public abstract class AbstractAminoAcid {
    protected int baseNumber;
    public abstract String getLabel();
    public abstract String getDescription();
    public abstract AbstractGameAction getAction();
}
