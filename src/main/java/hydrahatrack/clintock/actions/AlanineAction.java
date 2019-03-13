package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import hydrahatrack.clintock.aminoacids.Alanine;

public class AlanineAction extends AbstractGameAction {
    public AlanineAction(final int amount) {
        this.actionType = ActionType.ENERGY;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Alanine.NAME));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(
                            AbstractDungeon.player, AbstractDungeon.player,
                            new EnergizedBluePower(AbstractDungeon.player, this.amount), this.amount));

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
