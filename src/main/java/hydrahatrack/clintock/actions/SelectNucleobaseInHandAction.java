package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.cards.Adenine;
import hydrahatrack.clintock.cards.Cytosine;
import hydrahatrack.clintock.cards.Guanine;
import hydrahatrack.clintock.cards.Thymine;

public class SelectNucleobaseInHandAction extends AbstractGameAction {
    private int cardCount;

    public SelectNucleobaseInHandAction(final int cardCount) {
        this.cardCount = cardCount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
    }

    @Override
    public void update() {
        String TIP_MESSAGE = "Select a Nucleobase";

        if (this.duration == Settings.ACTION_DUR_FAST) {
            CardGroup group = new CardGroup(CardGroup.CardGroupType.HAND);
            group.addToBottom(new Adenine());
            group.addToBottom(new Cytosine());
            group.addToBottom(new Guanine());
            group.addToBottom(new Thymine());
            AbstractDungeon.gridSelectScreen.open(group, cardCount, TIP_MESSAGE, false);
        }
        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(
                    AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy()));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
        this.tickDuration();
    }
}
