package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.HistidineAction;

public class Histidine extends AbstractAminoAcid {
    public static final String NAME = "Histidine";
    public static final String CODE = "His";
    public static final String DESCRIPTION = "Remove all your #yDebuffs at the end of your turn.";
    public static final String[] DNA_CODONS = {"CAT", "CAC"};

    public Histidine() {}

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
        return new HistidineAction();
    }
}
