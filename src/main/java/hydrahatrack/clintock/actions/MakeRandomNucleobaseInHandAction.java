package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.cards.Adenine;
import hydrahatrack.clintock.cards.Cytosine;
import hydrahatrack.clintock.cards.Guanine;
import hydrahatrack.clintock.cards.Thymine;

import java.util.Random;

public class MakeRandomNucleobaseInHandAction extends AbstractGameAction {
    private int cardCount;

    public MakeRandomNucleobaseInHandAction(final int cardCount) {
        this.cardCount = cardCount;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        if (Settings.FAST_MODE) {
            this.startDuration = Settings.ACTION_DUR_FASTER;
        } else {
            this.startDuration = Settings.ACTION_DUR_MED;
        }
        this.duration = this.startDuration;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            Random random = new Random();
            for (int i = 0; i < cardCount; i++) {
                AbstractCard card;
                switch (random.nextInt(4)) {
                    case 0:
                        card = new Adenine();
                        break;
                    case 1:
                        card = new Cytosine();
                        break;
                    case 2:
                        card = new Guanine();
                        break;
                    case 3:
                    default:
                        card = new Thymine();
                        break;
                }
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card, 1));
            }
        }
        this.tickDuration();
    }
}
