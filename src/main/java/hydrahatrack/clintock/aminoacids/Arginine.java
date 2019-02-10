package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Arginine extends AbstractAminoAcid {
    private static final int BLOCK_AMOUNT = 3;

    public Arginine() {
        this.baseNumber = BLOCK_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Arginine (Gain " + this.baseNumber + " Block)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.baseNumber);
    }
}
