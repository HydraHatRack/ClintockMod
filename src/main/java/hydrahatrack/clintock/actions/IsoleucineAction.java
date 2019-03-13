package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;
import hydrahatrack.clintock.aminoacids.Isoleucine;

public class IsoleucineAction extends AbstractGameAction {
    public IsoleucineAction(final int amount) {
        this.actionType = ActionType.POWER;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Isoleucine.NAME));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new ThornsPower(AbstractDungeon.player, this.amount),
                            this.amount, true, AbstractGameAction.AttackEffect.NONE));

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
