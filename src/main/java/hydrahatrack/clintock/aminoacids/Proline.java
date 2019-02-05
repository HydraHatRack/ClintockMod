package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class Proline extends AbstractAminoAcid {
    private static final int VULNERABLE_AMOUNT = 2;

    public Proline() {
        this.baseNumber = VULNERABLE_AMOUNT;
    }

    @Override
    public String getLabel() {
        return "Pro";
    }

    @Override
    public AbstractGameAction getAction() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if (null != m) {
            return new ApplyPowerAction(m, AbstractDungeon.player,
                    new VulnerablePower(m, this.baseNumber, false),
                    this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
        }
        return null;
    }
}
