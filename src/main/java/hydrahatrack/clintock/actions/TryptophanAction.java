package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import hydrahatrack.clintock.aminoacids.Tryptophan;
import hydrahatrack.clintock.powers.LifeSagerPower;

public class TryptophanAction extends AbstractGameAction {
    public TryptophanAction(final int amount) {
        this.actionType = ActionType.DEBUFF;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Tryptophan.NAME));

            if (AbstractDungeon.player.hasPower(LifeSagerPower.POWER_ID)) {
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (null != m) {
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(
                                        m, -this.amount), -this.amount, true, AttackEffect.NONE));
                    }
                }
            } else {
                AbstractMonster m = AbstractDungeon.getRandomMonster();
                if (null != m) {
                    AbstractDungeon.actionManager.addToBottom(
                            new ApplyPowerAction(m, AbstractDungeon.player,
                                    new StrengthPower(m, -this.amount), -this.amount, true, AttackEffect.NONE));
                }
            }

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
