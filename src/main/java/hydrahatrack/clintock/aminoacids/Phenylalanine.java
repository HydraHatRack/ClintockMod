package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import hydrahatrack.clintock.actions.PhenylalanineAction;

public class Phenylalanine extends AbstractAminoAcid {
    public static final String NAME = "Phenylalanine";
    public static final String CODE = "Phe";
    public static final String DESCRIPTION = "Add a random uncommon card to your draw pile.";
    public static final String[] DNA_CODONS = {"TTT", "TTC"};
    private static final int CARD_AMOUNT = 1;

    public Phenylalanine() {
        this.baseNumber = CARD_AMOUNT;
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
        return new PhenylalanineAction(this.baseNumber);
    }
}
