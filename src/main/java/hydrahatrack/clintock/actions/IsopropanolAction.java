package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class IsopropanolAction extends AbstractGameAction {
    private float startingDuration;
    private AbstractRelic relic;

    public IsopropanolAction(final AbstractRelic relic) {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.relic = relic;
    }

    public void update() {
        boolean needsFlash = true;
        if (this.duration == this.startingDuration) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (AbstractCard.CardType.STATUS == c.type) {
                    if (needsFlash) {
                        relic.flash();
                        AbstractDungeon.actionManager.addToBottom(
                                new RelicAboveCreatureAction(AbstractDungeon.player, relic));
                        needsFlash = false;
                    }
                    AbstractDungeon.actionManager.addToBottom(
                            new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }
            }
            this.isDone = true;
        }
    }
}