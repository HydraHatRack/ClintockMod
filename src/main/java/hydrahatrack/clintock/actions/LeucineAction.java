package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.aminoacids.Leucine;
import hydrahatrack.clintock.powers.LifeSagerPower;

public class LeucineAction extends AbstractGameAction {
    public LeucineAction(final int amount) {
        this.actionType = ActionType.DAMAGE;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Leucine.NAME));

            if (AbstractDungeon.player.hasPower(LifeSagerPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAllEnemiesAction(AbstractDungeon.player,
                                DamageInfo.createDamageMatrix(this.amount, true),
                                DamageInfo.DamageType.THORNS, this.attackEffect, true));
            } else {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageRandomEnemyAction(
                                new DamageInfo(AbstractDungeon.player, this.amount),
                                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
