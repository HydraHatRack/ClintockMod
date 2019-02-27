package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.aminoacids.Valine;
import hydrahatrack.clintock.powers.PhosphatePower;

public class ValineAction extends AbstractGameAction {
    public ValineAction(final int amount) {
        this.actionType = ActionType.POWER;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Valine.LABEL));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new PhosphatePower(AbstractDungeon.player, this.amount),
                            this.amount, true, AbstractGameAction.AttackEffect.NONE));

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
