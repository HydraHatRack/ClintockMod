package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;

public class Tryptophan extends AbstractAminoAcid {
    private static final int STRENGTH_AMOUNT = 1;

    public Tryptophan() {
        this.baseNumber = STRENGTH_AMOUNT;
    }

    @Override
    public String getLabel() {
        return "Trp";
    }

    @Override
    public AbstractGameAction getAction() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if (null != m) {
            return new ApplyPowerAction(m, AbstractDungeon.player,
                    new LoseStrengthPower(m, this.baseNumber),
                    this.baseNumber, true, AbstractGameAction.AttackEffect.NONE);
        }
        return null;
    }
}
