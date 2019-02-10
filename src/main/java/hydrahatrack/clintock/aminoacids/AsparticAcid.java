package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BlurPower;

public class AsparticAcid extends AbstractAminoAcid {
    private static final int BLUR_AMOUNT = 1;

    public AsparticAcid() {
        this.baseNumber = BLUR_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Aspartic Acid (Block is not removed at the start of your next turn)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new BlurPower(AbstractDungeon.player, this.baseNumber),
                this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
    }
}
