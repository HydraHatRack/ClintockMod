package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.aminoacids.Methionine;

public class MethionineAction extends AbstractGameAction {
    public MethionineAction(final int amount) {
        this.actionType = ActionType.HEAL;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Methionine.LABEL));
            AbstractDungeon.actionManager.addToBottom(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.amount));

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
