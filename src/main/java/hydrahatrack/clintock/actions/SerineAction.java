package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPoisonOnRandomMonsterAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import hydrahatrack.clintock.aminoacids.Serine;
import hydrahatrack.clintock.powers.LifeSagerPower;

public class SerineAction extends AbstractGameAction {
    public SerineAction(final int amount) {
        this.actionType = ActionType.DEBUFF;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Serine.NAME));

            if (AbstractDungeon.player.hasPower(LifeSagerPower.POWER_ID)) {
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                        if (null != m && (!m.isDead) && (!m.isDying)) {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
                                    new PoisonPower(m, AbstractDungeon.player, this.amount), this.amount));
                        }
                    }
                }
            } else {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPoisonOnRandomMonsterAction(
                                AbstractDungeon.player, this.amount, true, AttackEffect.POISON));
            }

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
