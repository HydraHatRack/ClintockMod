package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hydrahatrack.clintock.aminoacids.Histidine;
import hydrahatrack.clintock.powers.LifeSagerPower;

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

            if (AbstractDungeon.player.hasPower(LifeSagerPower.POWER_ID)) {
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (null != m) {
                        AbstractDungeon.actionManager.addToBottom(
                                new RemoveAllBlockAction(m, AbstractDungeon.player));
                    }
                }
            } else {
                AbstractMonster m = AbstractDungeon.getRandomMonster();
                if (null != m) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(m, AbstractDungeon.player));
                }
            }

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
