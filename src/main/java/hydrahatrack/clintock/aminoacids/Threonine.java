package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.powers.SugarPower;

public class Threonine extends AbstractAminoAcid {
    private static final int SUGAR_AMOUNT = 2;

    public Threonine() {
        this.baseNumber = SUGAR_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Threonine (Gain " + this.baseNumber + " Sugars)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new SugarPower(AbstractDungeon.player, this.baseNumber),
                this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
    }
}
