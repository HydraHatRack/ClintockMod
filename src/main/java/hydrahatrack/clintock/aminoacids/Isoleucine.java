package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class Isoleucine extends AbstractAminoAcid {
    private static final int THORNS_AMOUNT = 2;

    public Isoleucine() {
        this.baseNumber = THORNS_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Isoleucine (Apply " + this.baseNumber + " Thorns)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new ThornsPower(AbstractDungeon.player, this.baseNumber),
                this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
    }
}
