package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.aminoacids.Arginine;

public class ArginineAction extends AbstractGameAction {
    public ArginineAction(final int amount) {
        this.actionType = ActionType.BLOCK;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Arginine.LABEL));
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.amount));

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
