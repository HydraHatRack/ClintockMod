package hydrahatrack.clintock.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import hydrahatrack.clintock.powers.FluorophorePower;

public class EurekaAction extends AbstractGameAction {
    private int fluorophoreGain;

    public EurekaAction(int fluorophoreGain) {
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.fluorophoreGain = fluorophoreGain;
    }

    public void update() {
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            this.isDone = true;
            return;
        }
        AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
        if (card.type == AbstractCard.CardType.POWER) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(
                            AbstractDungeon.player,
                            AbstractDungeon.player,
                            new FluorophorePower(AbstractDungeon.player, this.fluorophoreGain),
                            this.fluorophoreGain));
        }
        this.isDone = true;
    }
}
