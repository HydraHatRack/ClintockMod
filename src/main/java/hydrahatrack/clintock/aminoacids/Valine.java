package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.powers.PhosphatePower;

public class Valine extends AbstractAminoAcid {
    private static final int PHOSPHATE_AMOUNT = 2;

    public Valine() {
        this.baseNumber = PHOSPHATE_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Valine (Gain " + this.baseNumber + " Phosphate)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new PhosphatePower(AbstractDungeon.player, this.baseNumber),
                this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
    }
}
