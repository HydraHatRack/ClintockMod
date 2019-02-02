package hydrahatrack.clintock.aminoacids;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Arginine extends AbstractAminoAcid {
    @Override
    public String getLabel() {
        return "Arg";
    }

    @Override
    public AbstractGameAction getAction() {
        AbstractCreature m = AbstractDungeon.getRandomMonster();
        if (m != null) {
            DamageInfo info = new DamageInfo(AbstractDungeon.player, 3);
            return new DamageRandomEnemyAction(info, AbstractGameAction.AttackEffect.SMASH);
        }
        return null;
    }
}
