package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Cysteine extends AbstractAminoAcid {
    private static final int STRENGTH_AMOUNT = 1;

    public Cysteine() {
        this.baseNumber = STRENGTH_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Cysteine (Gain " + this.baseNumber + " Strength)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, this.baseNumber),
                this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
    }
}
