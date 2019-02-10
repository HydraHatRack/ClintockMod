package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Leucine extends AbstractAminoAcid {
    private static final int DAMAGE_AMOUNT = 4;

    public Leucine() {
        this.baseNumber = DAMAGE_AMOUNT;
    }

    @Override
    public String getLabel() {
        return " NL Leucine (Deal " + this.baseNumber + " damage to a random enemy)";
    }

    @Override
    public AbstractGameAction getAction() {
        return new DamageRandomEnemyAction(
                new DamageInfo(AbstractDungeon.player, this.baseNumber),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }
}
