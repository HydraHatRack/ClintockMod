package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.aminoacids.Histidine;

public class HistidineAction extends AbstractGameAction {
    public HistidineAction() {
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Histidine.LABEL));
            AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(AbstractDungeon.player));

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
