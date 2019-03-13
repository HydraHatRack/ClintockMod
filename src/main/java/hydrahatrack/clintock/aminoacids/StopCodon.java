package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class StopCodon extends AbstractAminoAcid {
    public static final String NAME = "Stop Codon";
    public static final String CODE = "STOP";
    public static final String DESCRIPTION = "?????";
    public static final String[] DNA_CODONS = {"TAG", "TAA", "TGA"};

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
        return null;
    }
}
