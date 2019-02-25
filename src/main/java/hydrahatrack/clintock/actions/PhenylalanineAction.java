package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.aminoacids.Phenylalanine;

public class PhenylalanineAction extends AbstractGameAction {
    public PhenylalanineAction(final int amount) {
        this.actionType = ActionType.SHUFFLE;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            AbstractDungeon.actionManager.addToBottom(
                    new TextAboveCreatureAction(AbstractDungeon.player, Phenylalanine.LABEL));
            AbstractDungeon.actionManager.addToBottom(
                    new MakeTempCardInDrawPileAction(
                            AbstractDungeon.getCard(AbstractCard.CardRarity.UNCOMMON, AbstractDungeon.cardRandomRng),
                            this.amount, true, true));

            this.isDone = true;
            return;
        }
        tickDuration();
    }
}
