package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import hydrahatrack.clintock.aminoacids.Glycine;
import hydrahatrack.clintock.powers.LifeSagerPower;

public class GlycineAction extends AbstractGameAction {
    public GlycineAction(final int weakAmount) {
        this.actionType = ActionType.DEBUFF;
        this.amount = weakAmount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Glycine.NAME));

            if (AbstractDungeon.player.hasPower(LifeSagerPower.POWER_ID)) {
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (null != m) {
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(m, AbstractDungeon.player,
                                        new WeakPower(m, this.amount, false),
                                        this.amount, true, AbstractGameAction.AttackEffect.NONE));
                    }
                }
            } else {
                AbstractMonster m = AbstractDungeon.getRandomMonster();
                if (null != m) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(m, AbstractDungeon.player,
                                    new WeakPower(m, this.amount, false),
                                    this.amount, true, AbstractGameAction.AttackEffect.NONE));
                }
            }

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
