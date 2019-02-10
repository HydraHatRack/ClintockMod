package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Glycine extends AbstractAminoAcid {
    private static final int WEAK_AMOUNT = 1;

    public Glycine() {
        this.baseNumber = WEAK_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Glycine (Apply " + this.baseNumber + " Weak to a random enemy)";
    }

    @Override
    public AbstractGameAction getAction() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if (null != m) {
            return new ApplyPowerAction(m, AbstractDungeon.player,
                    new WeakPower(m, this.baseNumber, false),
                    this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
        }
        return null;
    }
}
