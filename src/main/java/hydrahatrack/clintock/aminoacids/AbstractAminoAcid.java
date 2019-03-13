package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public abstract class AbstractAminoAcid {
    protected int baseNumber;
    public abstract String getName();
    public abstract String getCode();
    public abstract String getDescription();
    public abstract String[] getDnaCodons();
    public abstract AbstractGameAction getAction();
}
