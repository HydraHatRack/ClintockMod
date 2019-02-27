package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.cards.*;

import java.util.Random;

public class MakeRandomSequencerInHandAction extends AbstractGameAction {
    private int cardCostDiscount;

    public MakeRandomSequencerInHandAction(final int cardCostDiscount) {
        this.cardCostDiscount = cardCostDiscount;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER) {
            Random random = new Random();
            AbstractCard card;
            switch (random.nextInt(5)) {
                case 0:
                    card = new Bateson9000();
                    break;
                case 1:
                    card = new BiolelePrime();
                    break;
                case 2:
                    card = new LifeSager();
                    break;
                case 3:
                    card = new MendelPro();
                    break;
                case 4:
                default:
                    card = new SuperSeqX1();
                    break;
            }
            card.modifyCostForCombat(-this.cardCostDiscount);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card, 1));
        }
        this.tickDuration();
    }
}
