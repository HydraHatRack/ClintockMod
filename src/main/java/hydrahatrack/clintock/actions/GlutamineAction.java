package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.aminoacids.Glutamine;

public class GlutamineAction extends AbstractGameAction {
    public GlutamineAction(final int amount) {
        this.actionType = ActionType.SHUFFLE;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Glutamine.NAME));
            AbstractDungeon.actionManager.addToBottom(
                    new MakeTempCardInDrawPileAction(
                            AbstractDungeon.getCard(AbstractCard.CardRarity.COMMON, AbstractDungeon.cardRandomRng),
                            1, true, true));

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
